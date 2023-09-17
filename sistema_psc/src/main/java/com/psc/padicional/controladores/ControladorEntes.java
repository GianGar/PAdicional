package com.psc.padicional.controladores;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.psc.padicional.componentes.RenderizadorPaginas;
import com.psc.padicional.constantes.Constante;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.servicios.ServicioCategoria;
import com.psc.padicional.servicios.ServicioCodigoGestion;
import com.psc.padicional.servicios.ServicioEnte;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
@RequestMapping("/gestion/entidades")
public class ControladorEntes {
	
	char estado;
	private static final Log LOG = LogFactory.getLog(ControladorEntes.class);
	
	@Autowired
	@Qualifier("servicioEnte")
	private ServicioEnte servicioEnte;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioCategoria")
	private ServicioCategoria servicioCategoria;
	
	@Autowired
	@Qualifier("servicioCodigoGestion")
	private ServicioCodigoGestion servicioCodigoGestion;
	
	@GetMapping("/index")
	public String mostrar() {
		LOG.info("Clase: ControladorEntes. Entrando a Método: mostrar()");
		return "redirect:/gestion/entidades/listar";
	}
	
	@GetMapping("/salir")
	public String salir() {
		LOG.info("Clase: ControladorEntes. Entrando a Método: salir()");
		return "redirect:/panel/index";
	}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id",required=false) Integer id,
			@RequestParam(name="estado",required=false) String status) {
		ModelAndView mav = new ModelAndView(Vistas.ENTES_FORMULARIO);
		if (id == 0)
			estado = 'n';
		else
			estado = 'm';
		String titulo;
		LOG.info("Clase: ControladorEntes. Entrando a Método: formulario() id="+id);
		if (id==0) //NUEVA ENTIDAD
			{
			titulo = "Nueva entidad";
			mav.addObject("modeloEnte", new Ente());
			}
		else 
			{
			titulo = "Modificando entidad ''"+servicioEnte.buscarPorId(id).getNombre()+"''";
			mav.addObject("modeloEnte", servicioEnte.buscarPorId(id));
			}
		List<Categoria> categorias = servicioCategoria.listar();		
		LOG.info("Metodo: mostrarFormulario. Categorias: "+categorias.size());
		mav.addObject("categorias", categorias);
		mav.addObject("titulo",titulo);
		if (status != null)
			mav.addObject("estado", status);

		return mav;
		}
	
	@PostMapping("/nuevo")
	public String nuevo(@RequestParam(name="file",required=false) MultipartFile foto,
			@ModelAttribute(name="modeloEnte") Ente modeloEnte, Model model,
			RedirectAttributes flash) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());

		if (modeloEnte.getId() != 0 
				&& modeloEnte.getFoto() == null 
				&& servicioEnte.buscarPorId(modeloEnte.getId()).getFoto() != null) 
			//Condiciones: No es un create (no recibe id cero), recibe foto null y ya tenia foto cargada...
			//Entonces guardo el nombre de la foto original y asi evito que se ponga nulo.
			{
			String nombre_original = servicioEnte.buscarPorId(modeloEnte.getId()).getFoto();
			modeloEnte.setFoto(nombre_original);
			}
		modeloEnte.setCodigoGestion(usuario.getCodigoGestion());
		modeloEnte.setId_codigo_gestion(usuario.getId_codigo_gestion());
		Ente m = servicioEnte.nuevo(modeloEnte);
		Ente m2 = null;
		LOG.info("Clase: ControladorEntes. Entrando a Método: nuevo() Modelo: "+modeloEnte.toString());
		if (!foto.isEmpty() || foto.getSize() >= 1) {
			try {
				String nuevoNombre = m.getId()+"-"+foto.getOriginalFilename();
				byte[] bytes = foto.getBytes();
				String ruta = "C://ArchivosSistAdic//fotos//entes";
				Path rutaAbsoluta = Paths.get(ruta + "//" + nuevoNombre);
				Files.write(rutaAbsoluta, bytes);
				m.setFoto(nuevoNombre);
				m.setCodigoGestion(usuario.getCodigoGestion());
				m.setId_codigo_gestion(usuario.getId_codigo_gestion());
				m2 = servicioEnte.nuevo(m);
				m = m2;
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
			if (m == null)
				return "redirect:/gestion/entidades/formulario?id=0&estado=repetido";
			else
				if (estado == 'm')
					return "redirect:/gestion/entidades/formulario?id="+m.getId()+"&estado=modificado";
				else
					return "redirect:/gestion/entidades/formulario?id="+m.getId()+"&estado=creado";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="filtro",required=false) String filtro,
			@RequestParam(name="estado",required=false) String estado) {
		LOG.info("Clase: ControladorEntes. Entrando a Método: listar() - Filtro: '"+filtro+"', Estado: '"+estado+"'");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());
		if (filtro == null) filtro = "";
		ModelAndView mav = new ModelAndView(Vistas.ENTES);
		if (page == null)
			page = 0;
		Pageable userPageable = PageRequest.of(page, 30,Sort.by("nombre"));
		Page<Ente> ente = servicioEnte.pageables(usuario.getId_codigo_gestion(),filtro,userPageable);
		RenderizadorPaginas<Ente> renderizadorPaginas = new RenderizadorPaginas<Ente>("/gestion/entidades/listar", ente);

		mav.addObject("usuario", user.getUsername());		
		mav.addObject("page", renderizadorPaginas);
		mav.addObject("entes", ente);
		mav.addObject("estado", estado);
		mav.addObject("ruta",Constante.rutaSistema);
		mav.addObject("filtro", filtro);
		return mav;
		}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		LOG.info("Clase: ControladorEntes. Entrando a Método: eliminar() - ID: "+id);
		try{servicioEnte.eliminar(id);}
		catch(Exception e) {
			return listar(null,"","eliminacion_error");
		}
		return listar(null,"","eliminacion_ok");
	}

}
