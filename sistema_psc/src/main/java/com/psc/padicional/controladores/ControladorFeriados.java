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
import com.psc.padicional.entidades.Feriado;
import com.psc.padicional.servicios.ServicioFeriado;

@Controller
@RequestMapping("/gestion/feriados")
public class ControladorFeriados {
	
private static final Log LOG = LogFactory.getLog(ControladorFeriados.class);
private char operacion = 'n';

@Autowired
@Qualifier("servicioFeriado")
private ServicioFeriado servicioFeriado;
	
	@GetMapping("/index")
	public ModelAndView index() {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: index()");
		return listar("");
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(@RequestParam(name="estado",required=false) String estado) {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: listado()");
		ModelAndView mav = new ModelAndView(Vistas.FERIADOS);
		mav.addObject("feriados", servicioFeriado.listar());
		mav.addObject("estado", estado);
		return mav;
	}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id", required=true) int id, Model model) {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: formulario(), ID="+id);
		ModelAndView mav = new ModelAndView(Vistas.FERIADOS_FORMULARIO);
		Feriado feriado;
		String titulo = "";
		if (id==0) 
			{
			operacion = 'n';
			feriado = new Feriado();
			titulo = "Crear feriado";
			}
		else
			{
			operacion = 'm';
			feriado = servicioFeriado.buscarPorId(id);
			titulo = "Modificando feriado ("+feriado.getDescripcion()+")";
			}
			
		LOG.info("METODO: formulario()");
		model.addAttribute("feriado", feriado);
		model.addAttribute("titulo", titulo);
		return mav;
	}
	
	@PostMapping("/nuevo")
	public ModelAndView nuevo(@ModelAttribute(name="feriado") Feriado feriado, Model model) {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: nuevo()");
		servicioFeriado.nuevo(feriado);
		if (operacion == 'n')
			return listar("creado");
		else
			return listar("modificado");
	}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		LOG.info("Clase: ControladorFeriados. Entrando a Método: eliminar() - ID: "+id);
		servicioFeriado.eliminar(id);
		return listar("eliminado");
	}
	
	

}
