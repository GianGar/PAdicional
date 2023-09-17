package com.psc.padicional.controladores;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.constantes.Constante;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Guardia;
import com.psc.padicional.entidades.Objetivo;
import com.psc.padicional.servicios.ServicioGuardia;
import com.psc.padicional.servicios.ServicioObjetivo;

@Controller
@RequestMapping("/gestion/guardias")

public class ControladorGuardias {
	
	private static final Log LOG = LogFactory.getLog(ControladorGuardias.class);
	private int objetivoActual = 0;
	private char operacion = 'n';
	private String ente;
	private String objetivo;
	private int idEnte;
	private Objetivo modeloObjetivo;
	
	@Autowired
	@Qualifier("servicioGuardia")
	private ServicioGuardia servicioGuardia;
	
	@Autowired
	@Qualifier("servicioObjetivo")
	private ServicioObjetivo servicioObjetivo;
	
	@GetMapping("/index")
	public ModelAndView listado(@RequestParam(name="objetivo",required=true) int idObjetivo,
			@RequestParam(name="result",required=false) String result) {
		LOG.info("Clase: ControladorGuardias. Entrando a Método: listado() - idObjetivo: "+idObjetivo);
		objetivoActual = idObjetivo;
		ModelAndView mav = new ModelAndView(Vistas.GUARDIAS);
		modeloObjetivo = servicioObjetivo.buscarPorId(objetivoActual);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ente = modeloObjetivo.getEnte().getNombre();
		objetivo = modeloObjetivo.getDescripcion();
		idEnte = modeloObjetivo.getEnte().getId();
		mav.addObject("usuario", user.getUsername());
		mav.addObject("guardias", servicioGuardia.listar(idObjetivo));
		mav.addObject("result", result);
		mav.addObject("objetivo", objetivo);
		mav.addObject("ente", ente);
		mav.addObject("idEnte", idEnte);
		return mav;
	}
	
	@PostMapping("/nueva")
	public String nueva(@ModelAttribute(name="modeloGuardia") Guardia modeloGuardia, Model model) {
		LOG.info("Clase: ControladorGuardias. Entrando a Método: nueva() - modeloGuardia: "+modeloGuardia.toString());
		modeloGuardia.setIdObjetivo(objetivoActual);
		int duracion = Utilidades.getDuracionGuardia(modeloGuardia.getHoraEntrada(), modeloGuardia.getHoraSalida());
		System.out.println("Duración: "+duracion);
		if (duracion > Constante.MAXIMO_GUARDIA)
			return "redirect:/gestion/guardias/index?objetivo="+objetivoActual+"&result=excedido";
		Guardia mo = servicioGuardia.nueva(modeloGuardia);
		int guardia = 0;
		if(null != mo)
			{
			mo.setIdObjetivo(objetivoActual);
			guardia = mo.getId();
			}
		if (operacion == 'n')
			return "redirect:/gestion/guardias/formulario?id="+guardia+"&result=ok";
		else
			return "redirect:/gestion/guardias/formulario?id="+guardia+"&result=modificada";
	}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="result",required=false) String result) {
		ModelAndView mav = new ModelAndView(Vistas.GUARDIAS_FORMULARIO);
		if (id == 0)
			operacion = 'n';
		else
			operacion = 'm';
		String titulo = "Nueva guardia";
		if (id != 0)
			titulo = "Modificar guardia";
		LOG.info("Clase: ControladorGuardias. Entrando a Método: formulario() - Id: "+id);
		Guardia mo = new Guardia();
		if (id==0)
			{
			mo.setIdObjetivo(objetivoActual);
			mav.addObject("modeloGuardia", mo);
			}
		else 
			{
			mav.addObject("modeloGuardia", servicioGuardia.buscarPorId(id));
			}
		mav.addObject("objetivo", objetivo);
		mav.addObject("ente", ente);
		mav.addObject("idEnte", idEnte);
		mav.addObject("idObjetivo", modeloObjetivo.getId());
		mav.addObject("titulo", titulo);
		mav.addObject("result", result);
		return mav;
		}
	
	@GetMapping("eliminar")
	public ModelAndView eliminarObjetivo(@RequestParam(name="id", required=true) int id) {
		LOG.info("Clase: ControladorGuardias. Entrando a Método: eliminar() - Id: "+id);
		servicioGuardia.eliminar(id);
		return listado(objetivoActual,"borr");
		
	}

}
