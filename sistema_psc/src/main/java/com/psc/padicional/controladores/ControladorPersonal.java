package com.psc.padicional.controladores;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.constantes.Constante;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Destino;
import com.psc.padicional.entidades.Jerarquia;
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Policia;
import com.psc.padicional.entidades.Suspension;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.servicios.ServicioDestino;
import com.psc.padicional.servicios.ServicioEnte;
import com.psc.padicional.servicios.ServicioJerarquia;
import com.psc.padicional.servicios.ServicioPeriodo;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioPolicia;
import com.psc.padicional.servicios.ServicioServicio;
import com.psc.padicional.servicios.ServicioSuspension;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
@RequestMapping("/gestion/personal")
public class ControladorPersonal {
	
	private static final Log LOG = LogFactory.getLog(ControladorPersonal.class);
	private char operacion = 'n';
	private Integer idPhActual;
	private PersonalHabilitado phActual;
	
	@Autowired
	@Qualifier("servicioServicio")
	private ServicioServicio servicioServicio;
	
	@Autowired
	@Qualifier("servicioPersHab")
	private ServicioPersHab servicioPH;
	
	@Autowired
	@Qualifier("servicioEnte")
	private ServicioEnte servicioEnte;
	
	@Autowired
	@Qualifier("servicioPolicia")
	private ServicioPolicia servicioPolicia;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioJerarquia")
	private ServicioJerarquia servicioJerarquia;
	
	@Autowired
	@Qualifier("servicioDestino")
	private ServicioDestino servicioDestino;
	
	@Autowired
	@Qualifier("servicioSuspension")
	private ServicioSuspension servicioSuspension;
	
	@Autowired
	@Qualifier("servicioPeriodo")
	private ServicioPeriodo servicioPeriodo;
		
	@GetMapping("/index")
	public String index() {
		return "redirect:/gestion/personal/listar";
		}
	
	@GetMapping("/listar")
	public ModelAndView listar(@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="filtro",required=false) String filtro) {
		if (filtro == null) filtro = "";

		LOG.info("Entrando a método: mostrarEntes("+filtro+")");
		ModelAndView mav = new ModelAndView(Vistas.PERSONAL_LISTADO);
		
		Pageable userPageable = PageRequest.of(page, 30,Sort.by("policia.persona.nombre"));
		Page<PersonalHabilitado> pagePH = servicioPH.pageable(filtro,userPageable);
		RenderizadorPaginas<PersonalHabilitado> renderizadorPaginas = new RenderizadorPaginas<PersonalHabilitado>("/gestion/personal/listar", pagePH);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());
		
		List <PersonalHabilitado> modeloPH = servicioPH.listar(usuario.getId_codigo_gestion(),filtro);
		LOG.info("Saliendo de método: mostrarEntes, encontrados: "+modeloPH.size());
		mav.addObject("page", renderizadorPaginas);
		mav.addObject("modeloPH", pagePH);
		Integer mes = Utilidades.mesProximo();
		Integer anio = Utilidades.anioActual();
		if (mes == 1) anio++;
		mav.addObject("proximoPeriodo", mes+"/"+anio);
		mav.addObject("filtro", filtro);
		return mav;
		}
	
	@GetMapping("/cargar")
	public ModelAndView cargarPersonalHabilitado(@RequestParam(name="filtro",required=false) String filtro) {
		if (filtro == null) filtro  ="";
		LOG.info("Entrando a método: cargarPersonalHabilitado("+filtro+")");
		ModelAndView mav = new ModelAndView(Vistas.PERSONAL_CARGAR);
		List<Policia> modeloPolicia = servicioPolicia.listarAptosAdicional(filtro);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("modeloPolicia", modeloPolicia);
		mav.addObject(Constante.USUARIO, user.getUsername());
		return mav;
		}
	
	@PostMapping("/nuevo")
	public String nuevo(@RequestParam(name="file",required=false) MultipartFile foto,
			@ModelAttribute(name="modeloPersonal") PersonalHabilitado modeloPersonal, Model model,
			RedirectAttributes flash) {
		LOG.info("Entrando al método: nuevo - Parámetros: "+modeloPersonal.toString());
		
		if (modeloPersonal.getId() != 0 
				&& modeloPersonal.getFoto() == null 
				&& servicioPH.buscarPorId(modeloPersonal.getId()).getFoto() != null) 
			//Condiciones: No es un create (no recibe id cero), recibe foto null y ya tenia foto cargada...
			//Entonces guardo el nombre de la foto original y asi evito que se ponga nulo.
			{
			//Cumplió las 3 condiciones!!
			String nombre_original = servicioPH.buscarPorId(modeloPersonal.getId()).getFoto();
			modeloPersonal.setFoto(nombre_original);
			}
		PersonalHabilitado mo = modeloPersonal;
		try {
		mo = servicioPH.nuevo(modeloPersonal);
			}
		catch (Exception e){
			
			}
		PersonalHabilitado mo2 = null;
		if (!foto.isEmpty()) {
			try {
				String nuevoNombre = mo.getId()+"-"+foto.getOriginalFilename();
				byte[] bytes = foto.getBytes();
				String ruta = "C://ArchivosSistAdic//fotos//personal";
				Path rutaAbsoluta = Paths.get(ruta + "//" + nuevoNombre);
				Files.write(rutaAbsoluta, bytes);
				mo.setFoto(nuevoNombre);
				mo2 = servicioPH.nuevo(mo);
				mo = mo2;
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if (mo == null) 
			return "redirect:/gestion/personal/perfil?estado=error&id=0";
		else
			return "redirect:/gestion/personal/perfil?estado=creado&id="+mo.getId();
		}
	
	@GetMapping("/formulario")
	public ModelAndView mostrarFormulario(@RequestParam(name="id",required=true) int id) {
		ModelAndView mav = new ModelAndView(Vistas.PERSONAL_NUEVO);
	PersonalHabilitado mph = new PersonalHabilitado();
	mph.setIdEfectivo(id);
	mav.addObject("modeloPersonal", mph);
	mav.addObject("efectivo", Utilidades.formatoMayuscula(servicioPolicia.buscarPorId(id).getPersona().getNombre()));
	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	mav.addObject("usuario", user.getUsername());
	return mav;
		}
	
	@GetMapping("/editar")
	public ModelAndView editarFormulario(@RequestParam(name="id",required=true) int id) {
	ModelAndView mav = new ModelAndView(Vistas.PERSONAL_NUEVO);
	PersonalHabilitado mph = servicioPH.buscarPorId(id);
	mav.addObject("modeloPersonal", mph);
	List<Jerarquia> jerarquias = servicioJerarquia.listar();
	mav.addObject("efectivo", Utilidades.formatoMayuscula(servicioPH.buscarPorId(id).getPolicia().getPersona().getNombre()));
	mav.addObject("jerarquias", jerarquias);
	return mav;
	}
	
	@GetMapping("/datospoliciales")
	public ModelAndView editarDatosPoliciales(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="modificado",required=false) String modificado) {
	ModelAndView mav = new ModelAndView(Vistas.PERSONAL_DATOSPERSONALES);
	Policia pol = servicioPolicia.buscarPorId(id);
	mav.addObject("modeloPolicia", pol);
	List<Jerarquia> jerarquias = servicioJerarquia.listar();
	List<Destino> destinos = servicioDestino.listar();
	mav.addObject("jerarquias", jerarquias);	
	mav.addObject("destinos", destinos);
	mav.addObject("efectivo", Utilidades.formatoMayuscula(pol.getPersona().getNombre()));
	PersonalHabilitado ph = servicioPH.obtenerPH(pol);
	mav.addObject("ph", ph);
	mav.addObject("modificado", modificado);
	return mav;
	}
	
	@PostMapping("/datpolnuevo")
	public String nuevo(@ModelAttribute(name="modeloPolicia") Policia p, Model model) {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: nuevo()");
		Policia pol = servicioPolicia.buscarPorId(p.getId());
		pol.setIdDestino(p.getIdDestino());
		pol.setDestino(p.getDestino());
		pol.setJerarquia(p.getJerarquia());
		pol.setIdJerarquia(p.getIdJerarquia());
		servicioPolicia.nuevo(pol);
		return "redirect:/gestion/personal/datospoliciales?id="+p.getId()+"&modificado";
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		try{servicioPH.eliminar(id);}
		catch(Exception e) {
			return listar(0,"");
		}
		return listar(0,"");
	}
	
	@GetMapping("/perfil")
	public ModelAndView perfil(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="estado",required=false) String estado) {
	ModelAndView mav = new ModelAndView(Vistas.PERSONAL_PERFIL);
	PersonalHabilitado mph = servicioPH.buscarPorId(id);
	mav.addObject("modeloPH", mph);
	Integer mesActual = Utilidades.mesActual();
	Integer anioActual = Utilidades.anioActual();
	Integer mesProx = Utilidades.mesProximo();
	Integer anioProx = Utilidades.anioActual();
	Integer generadoActual = null;
	Integer generadoProx=null;

	Periodo p = servicioPeriodo.buscarPorFecha(anioActual, mesActual);
	if (p != null)
		generadoActual = 2;
	p = servicioPeriodo.buscarPorFecha(anioProx, mesProx);
	if (p != null)
		generadoProx = 2;
	if (mesProx == 1) anioProx++;
	mav.addObject("mesActual", mesActual);
	mav.addObject("mesProx", mesProx);
	mav.addObject("mesActualNombre", Utilidades.nombreMes(mesActual));
	mav.addObject("mesProxNombre", Utilidades.nombreMes(mesProx));
	mav.addObject("anioActual", anioActual);
	mav.addObject("anioProx", anioProx);
	mav.addObject("generadoActual", generadoActual);
	mav.addObject("generadoProx", generadoProx);
	mav.addObject("estado", estado);
	return mav;
	}
	
	@GetMapping("/suspensiones")
	public ModelAndView suspensiones(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="estado",required=false) String estado) {
	this.idPhActual = id;
	ModelAndView mav = new ModelAndView("suspensiones");
	PersonalHabilitado ph = servicioPH.buscarPorId(id);
	phActual = ph;
	List<Suspension> suspensiones = servicioSuspension.suspensionesDe(id);
	mav.addObject("ph", ph);
	mav.addObject("suspensiones", suspensiones);
	mav.addObject("estado", estado);
	return mav;
	}
	
	@GetMapping("/suspensiones/formulario")
	public ModelAndView formulario(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="result",required=false) String result) {
		ModelAndView mav = new ModelAndView(Vistas.SUSPENSIONES_FORMULARIO);
		if (id == 0)
			operacion = 'n';
		else
			operacion = 'm';
		String titulo = "Nueva suspensión";
		if (id != 0)
			titulo = "Modificar suspensión";
		LOG.info("Clase: ControladorSuspension. Entrando a Método: formulario() - Id: "+id);
		Suspension s = new Suspension();
		if (id==0)
			{
			s.setIdPH(idPhActual);
			mav.addObject("suspension", s);
			}
		else 
			{
			mav.addObject("suspension", servicioSuspension.buscarPorId(id));
			}
		mav.addObject("ph", phActual);
		mav.addObject("titulo", titulo);
		mav.addObject("result", result);
		return mav;
		}
	
	@PostMapping("/suspensiones/nueva")
	public String nueva(@ModelAttribute(name="suspension") Suspension suspension, Model model) {
		LOG.info("Clase: ControladorPersonal. Entrando a Método: nueva suspensión() - suspensión: "+suspension.toString());
		suspension.setIdPH(idPhActual);
		Suspension s = servicioSuspension.nuevo(suspension);
		int id_susp = 0;
		if(null != s)
			{
			s.setIdPH(idPhActual);
			id_susp = s.getId();
			}
		if (operacion == 'n')
			return "redirect:/gestion/personal/suspensiones/formulario?id="+id_susp+"&result=ok";
		else
			return "redirect:/gestion/personal/suspensiones/formulario?id="+id_susp+"&result=modificada";
	}
	

}
