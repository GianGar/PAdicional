package com.psc.padicional.controladores;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
public class ControladorLogin {
	private static final Log LOG = LogFactory.getLog(ControladorLogin.class);
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("Clase: ControladorLogin. Entrando a Método: showLoginForm()");
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return Vistas.LOGIN;
	}
	
	@GetMapping({"/loginsuccess","/"})
	public String iniciarSesion() {
		LOG.info("Clase: ControladorLogin. Entrando a Método: iniciarSesion()");
		return "redirect:/panel/index";
	}
	
	@GetMapping("/logout")
	public String cerrarSesion() {
		LOG.info("Clase: ControladorLogin. Entrando a Método: cerrarSesion()");
		return "redirect:/login?logout";
	}
	
	

}
