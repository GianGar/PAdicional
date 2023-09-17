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
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.servicios.ServicioPeriodo;

@Controller
@RequestMapping("/configuracion/periodos")
public class ControladorPeriodo {

	private static final Log LOG = LogFactory.getLog(ControladorPeriodo.class);
	private char operacion = 'n';
	
	@Autowired
	@Qualifier("servicioPeriodo")
	private ServicioPeriodo servicioPeriodo;
	
	@GetMapping("/index")
	public String mostrar() {
		LOG.info("Clase: ControladorPeriodo. Entrando a Método: mostrar()");
		return "redirect:/configuracion/periodos/listar";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(@RequestParam(name="estado",required=false) String estado) {
		LOG.info("Clase: ControladorPeriodo. Entrando a Método: listar()");
		ModelAndView mav = new ModelAndView(Vistas.PERIODOS);
		mav.addObject("periodos", servicioPeriodo.listar());
		mav.addObject("estado", estado);
		return mav;
		}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id", required=true) int id, Model model) {
		LOG.info("Clase: ControladorPeriodo. Entrando a Método: formulario()");
		ModelAndView mav = new ModelAndView(Vistas.PERIODOS_FORMULARIO);
		Periodo periodo;
		String titulo = "";
		if (id==0) 
			{
			periodo = new Periodo();
			operacion = 'n';
			titulo = "Crear período";
			}
		else
			{
			periodo = servicioPeriodo.buscarPorId(id);
			operacion = 'm';
			titulo = "Modificando período";
			}
		LOG.info("METODO: formulario()");
		mav.addObject("periodo", periodo);
		mav.addObject("titulo", titulo);
		return mav;
	}
	
	@PostMapping("/nueva")
	public ModelAndView nueva(@ModelAttribute(name="modeloCategoria") Periodo periodo, Model model) {
		LOG.info("Clase: ControladorPeriodo. Entrando a Método: nueva()");
		servicioPeriodo.nuevo(periodo); //Devolvió algo, lo insertó en la base...
		if (operacion == 'n')
			return listar("creacion");
		else
			return listar("modificacion");
		
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		LOG.info("Clase: ControladorPeriodo. Entrando a Método: eliminar()");
		servicioPeriodo.eliminar(id);
		return listar("eliminacion");
	}
	
}
