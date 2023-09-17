package com.psc.padicional.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.servicios.ServicioServicio;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
@RequestMapping("/adicional/estadisticas")
public class ControladorEstadisticas {
	
	@Autowired
	@Qualifier("servicioServicio")
	private ServicioServicio servicioServicio;
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/proximo_mes")
	public ModelAndView listado() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());
		ModelAndView mav = new ModelAndView(Vistas.ESTADISTICAS_PROX_MES);
		Integer mes = Utilidades.mesProximo();
		Integer anio = Utilidades.anioActual();
		if (mes == 1) anio++;
		List<Servicio> todos = servicioServicio.listar(anio,mes, 0, "todos", "",usuario.getId_codigo_gestion());
		List<Servicio> asignados = servicioServicio.listar(anio,mes, 0, "asignados", "",usuario.getId_codigo_gestion());
		mav.addObject("periodoProximo", mes+"/"+anio);
		int serviciosTotales = todos.size();
		int serviciosAsignados = asignados.size();
		double porcentaje = (double)serviciosAsignados * 100/(double)serviciosTotales;
		mav.addObject("serviciosTotales", serviciosTotales);
		mav.addObject("serviciosAsignados", serviciosAsignados);
		mav.addObject("porcentaje", Utilidades.redondear(porcentaje, 2));
		return mav;
		}
	
	public void graficoAsignacion() {
		
	}

}
