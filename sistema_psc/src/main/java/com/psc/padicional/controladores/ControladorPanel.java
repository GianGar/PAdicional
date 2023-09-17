package com.psc.padicional.controladores;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.psc.padicional.constantes.Vistas;

@Controller
@RequestMapping("/panel")
public class ControladorPanel {
	
	private static final Log LOG = LogFactory.getLog(ControladorPanel.class);
	
	@GetMapping("/index")
	public ModelAndView mostrar() {
		LOG.info("Clase: ControladorPanel. Entrando a Método: mostrar()");
		ModelAndView mav = new ModelAndView(Vistas.PANEL);
		return mav;
	}
}
