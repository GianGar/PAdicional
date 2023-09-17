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
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.Objetivo;
import com.psc.padicional.servicios.ServicioCategoria;
import com.psc.padicional.servicios.ServicioEnte;
import com.psc.padicional.servicios.ServicioObjetivo;

@Controller
@RequestMapping("/gestion/objetivos")

public class ControladorObjetivos {
	
	private static final Log LOG = LogFactory.getLog(ControladorObjetivos.class);
	private int idEntidadActual = 0;
	private Ente ente;
	
	@Autowired
	@Qualifier("servicioObjetivo")
	private ServicioObjetivo servicioObjetivo;
	
	@Autowired
	@Qualifier("servicioEnte")
	private ServicioEnte servicioEnte;
	
	@Autowired
	@Qualifier("servicioCategoria")
	private ServicioCategoria servicioCategoria;
	
	@GetMapping("/index")
	public ModelAndView listado(@RequestParam(name="entidad",required=true) Integer entidad,
			@RequestParam(name="estado",required=false) String estado) {
		LOG.info("Clase: ControladorObjetivos. Entrando a Método: listado() idEnte: "+ente);
		idEntidadActual = entidad;
		ModelAndView mav = new ModelAndView(Vistas.OBJETIVOS);
		mav.addObject("objetivos", servicioObjetivo.listar(idEntidadActual));
		ente = servicioEnte.buscarPorId(idEntidadActual);
		mav.addObject("entidad", ente);
		mav.addObject("estado",estado);
		return mav;
	}
	
	@GetMapping("/formulario")
	public ModelAndView formulario(@RequestParam(name="id",required=true) int id,
			@RequestParam(name="estado",required=false) String estado) {
		ModelAndView mav = new ModelAndView(Vistas.OBJETIVOS_FORMULARIO);
		String titulo = "Nuevo objetivo";
		if (id != 0)
			{
			Objetivo modeloObjetivo = servicioObjetivo.buscarPorId(id);
			titulo = "Modificando objetivo '"+modeloObjetivo.getDescripcion()+"'";
			}
		LOG.info("Clase: ControladorObjetivos. Entrando a Método: formulario() Id: "+id);
		Objetivo mo = new Objetivo();
		if (id==0)
			{
			mo.setIdEnte(idEntidadActual);
			mav.addObject("objetivo", mo);
			}
		else 
			mav.addObject("objetivo", servicioObjetivo.buscarPorId(id));
		mav.addObject("nombreEntidad", ente.getNombre());
		mav.addObject("idEnte", ente.getId());
		mav.addObject("titulo", titulo);
		mav.addObject("estado", estado);
		return mav;
		}
	
	@PostMapping("/nuevo")
	public String nuevo(@ModelAttribute(name="objetivo") Objetivo objetivo, Model model) {
		LOG.info("Clase: ControladorObjetivos. Entrando a Método: nuevo(), Objetivo: "+objetivo.toString());
		String estado;
		Objetivo prueba = servicioObjetivo.buscarPorId(objetivo.getId());
		if (prueba == null)
			estado = "creado";
		else
			estado = "modificado";
		Objetivo mo;
		objetivo.setIdEnte(idEntidadActual);
		int idObjetivo =0;
		try	{
		mo = servicioObjetivo.nuevo(objetivo);
		idObjetivo = mo.getId();
			}
		catch(Exception e) {
			return "redirect:/gestion/objetivos/index?entidad="+idEntidadActual+"&estado=modificacion_error";
			}		
		if(null != mo)
			{
			mo.setIdEnte(idEntidadActual);
			}
		return "redirect:/gestion/objetivos/formulario?id="+idObjetivo+"&estado="+estado;
	}
	
	@GetMapping("eliminar")
	public ModelAndView eliminar(@RequestParam(name="id", required=true) int id) {
		LOG.info("Clase: ControladorObjetivos. Entrando a Método: eliminar() - ID: "+id);
		System.out.println("antes del try - Entrando a eliminar el objetivo numero "+id);
		try	{
			System.out.println("despues del try - Entrando a eliminar el objetivo numero "+id);
			servicioObjetivo.eliminar(id);
			}
		catch(Exception e) {
			return listado(idEntidadActual,"eliminacion_error");
			}
		return listado(idEntidadActual,"eliminado");
	}

}