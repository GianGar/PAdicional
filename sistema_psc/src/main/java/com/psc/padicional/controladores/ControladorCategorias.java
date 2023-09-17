package com.psc.padicional.controladores;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.servicios.ServicioCategoria;

@Controller
@RequestMapping("/configuracion/categorias")
public class ControladorCategorias {

	private static final Log LOG = LogFactory.getLog(ControladorCategorias.class);
	private char operacion = 'n';
	
	@Autowired
	@Qualifier("servicioCategoria")
	private ServicioCategoria servicioCategoria;
	
	@GetMapping("/index")
	public String mostrar() {
		LOG.info("Clase: ControladorCategorias. Entrando a Método: mostrar()");
		return "redirect:/configuracion/categorias/listar";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(@RequestParam(name="estado",required=false) String estado) {
		LOG.info("Clase: ControladorCategorias. Entrando a Método: listar()");
		ModelAndView mav = new ModelAndView(Vistas.CATEGORIAS);
		mav.addObject("categorias", servicioCategoria.listar());
		mav.addObject("estado", estado);
		return mav;
		}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id", required=true) int id, Model model) {
		LOG.info("Clase: ControladorCategorias. Entrando a Método: formulario()");
		ModelAndView mav = new ModelAndView(Vistas.CATEGORIAS_FORMULARIO);
		Categoria categoria;
		String titulo = "";
		if (id==0) 
			{
			categoria = new Categoria();
			operacion = 'n';
			titulo = "Crear categoría";
			}
		else
			{
			categoria = servicioCategoria.buscarPorId(id);
			operacion = 'm';
			titulo = "Modificando categoría ("+categoria.getNombre()+")";
			}
		LOG.info("METODO: formulario()");
		mav.addObject("modeloCategoria", categoria);
		mav.addObject("titulo", titulo);
		return mav;
	}
	
	@PostMapping("/nueva")
	public ModelAndView nueva(@ModelAttribute(name="modeloCategoria") Categoria modeloCategoria, Model model) {
		LOG.info("Clase: ControladorCategorias. Entrando a Método: nueva()");
		servicioCategoria.nuevo(modeloCategoria); //Devolvió algo, lo insertó en la base...
		if (operacion == 'n')
			return listar("creacion");
		else
			return listar("modificacion");
		
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		LOG.info("Clase: ControladorCategorias. Entrando a Método: eliminar()");
		servicioCategoria.eliminar(id);
		return listar("eliminacion");
	}
	
}
