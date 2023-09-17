package com.psc.padicional.controladores;

import java.time.YearMonth;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.psc.padicional.componentes.RenderizadorPaginas;
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.constantes.Constante;
import com.psc.padicional.constantes.Vistas;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.Guardia;
import com.psc.padicional.entidades.Objetivo;
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Policia;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.servicios.ServicioCategoria;
import com.psc.padicional.servicios.ServicioEnte;
import com.psc.padicional.servicios.ServicioFeriado;
import com.psc.padicional.servicios.ServicioGuardia;
import com.psc.padicional.servicios.ServicioObjetivo;
import com.psc.padicional.servicios.ServicioPeriodo;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioPolicia;
import com.psc.padicional.servicios.ServicioServicio;
import com.psc.padicional.servicios.ServicioUsuario;

@Controller
@RequestMapping("/gestion/servicios")
public class ControladorServicios {
	private static final Log LOG = LogFactory.getLog(ControladorServicios.class);
	
	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	@Qualifier("servicioPolicia")
	private ServicioPolicia servicioPolicia;
	
	@Autowired
	@Qualifier("servicioEnte")
	private ServicioEnte servicioEnte;
	
	@Autowired
	@Qualifier("servicioPersHab")
	private ServicioPersHab servicioPH;
	
	@Autowired
	@Qualifier("servicioServicio")
	private ServicioServicio servicioServicio;
	
	@Autowired
	@Qualifier("servicioPeriodo")
	private ServicioPeriodo servicioPeriodo;
	
	@Autowired
	@Qualifier("servicioObjetivo")
	private ServicioObjetivo servicioObjetivo;
	
	@Autowired
	@Qualifier("servicioGuardia")
	private ServicioGuardia servicioGuardia;
	
	@Autowired
	@Qualifier("servicioFeriado")
	private ServicioFeriado servicioFeriado;
	
	@Autowired
	@Qualifier("servicioCategoria")
	private ServicioCategoria servicioCategoria;
	
	Integer mes = Utilidades.mesActual();
	Integer anio = Utilidades.anioActual();
	int servicioActual;
	int enteActual;
	int phActual;
	int mesActual = Utilidades.mesActual();
	int anioActual = Utilidades.anioActual();
	String nombreMes;
	String estadoFiltro;
	String informacionFiltro;
	Integer diaFiltro;
	List<Categoria> categorias;
	float [] categ = new float[100];
	int [] feriados = new int[32];
	boolean esFeriado;
	
	@GetMapping("/asignar_a")
	public String asignarA(
			@RequestParam(name="id",required=true) Integer servicio,
			@RequestParam(name="persona",required=true) Integer persona) {
		//Recibe id del servicio.
		PersonalHabilitado ph = servicioPH.buscarPorId(persona);
		Servicio s = servicioServicio.buscarPorId(servicio);
		//Verificar si ya se encuentra asignado, si es así, cancelar.
		if (s.getEfectivo() != null)
			return "redirect:/gestion/servicios/efectivo?id="+ph.getId()+"&anio="+anioActual+"&mes="+mesActual+"&estado=fail";
		//Ya sabemos que no está asignado, se procede a asignar.
		s.setEfectivo(ph.getIdEfectivo());
		s.setDestino(ph.getPolicia().getDestino().getDescripcion());
		s.setJerarquia(ph.getPolicia().getJerarquia().getDescripcion());
		servicioServicio.nuevo(s);
		return "redirect:/gestion/servicios/efectivo?id="+ph.getId()+"&anio="+anioActual+"&mes="+mesActual+"&estado=ok";
	}
	
	@GetMapping("/seleccionar")
	public ModelAndView seleccionar(
			@RequestParam(name="id",required=false) Integer id,
			@RequestParam(name="ente",required=false) Integer ente,
			@RequestParam(name="dia",required=false) Integer dia,
			@RequestParam(name="mes",required=false) Integer mes,
			@RequestParam(name="anio",required=false) Integer anio){
		ModelAndView mav = new ModelAndView(Vistas.SERVICIOS_SELECCIONAR);
		if (id != null)
			phActual = id;
		PersonalHabilitado ph = servicioPH.buscarPorId(phActual);
		if (mes != null)
			mesActual = mes;
		if (anio != null)
			anioActual = anio;
		if (ente == null)
			enteActual=0;
		else
			enteActual=ente;
		if (dia == null)
			this.diaFiltro = 0;
		else
			this.diaFiltro = dia;
		String fecha = Utilidades.armarFechaMYSQL(diaFiltro, mesActual, anioActual);
		System.out.println("Año: "+anioActual);
		System.out.println("Mes: "+mesActual);
		System.out.println("Persona: "+phActual);
		System.out.println("Ente: "+enteActual);
		System.out.println("Fecha: "+fecha);
		List <Servicio> servicios = servicioServicio.serviciosDisponibles(anioActual,mesActual, ph, enteActual, fecha);
		List <Ente> entes = servicioServicio.entesConServiciosDisponibles(anioActual,mesActual, ph);
		mav.addObject("modeloPH", servicioPH.buscarPorId(phActual));
		mav.addObject("servicios", servicios);
		mav.addObject("periodo", Utilidades.nombreMes(mesActual)+" de "+anioActual);
		mav.addObject("entes", entes);
		mav.addObject("cantidad", servicios.size());
		mav.addObject("persona", phActual);
		return mav;
	}
	
	@GetMapping("/efectivo")
	public ModelAndView serviciosEfectivo(
			@RequestParam(name="id",required=true) Integer id,
			@RequestParam(name="anio",required=true) Integer anio, 
			@RequestParam(name="mes",required=true) Integer mes,
			@RequestParam(name="estado",required=false) String estado){
		ModelAndView mav = new ModelAndView(Vistas.SERVICIOS_EFECTIVO);
		PersonalHabilitado mph = servicioPH.buscarPorId(id);
		mav.addObject("modeloPH", mph);
		Integer idPolicia = servicioPH.buscarPorId(id).getIdEfectivo();
		List <Servicio> servicios = servicioServicio.serviciosEfectivo(anio,mes,idPolicia);
		Integer horas = Utilidades.cantidadHorasServicios(servicios);
		mav.addObject("servicios", servicios);
		mav.addObject("horas", horas);
		mav.addObject("cantidadServicios", servicios.size());
		mav.addObject("periodo", Utilidades.nombreMes(mes)+ " de "+anio);
		mav.addObject("mes", mes);
		mav.addObject("anio", anio);
		mav.addObject("estado", estado);
		return mav;
	}
	
	
	
	@GetMapping("/listado")
	public ModelAndView listado(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="anio") Integer anio,
			@RequestParam(name="mes") Integer mes,
			@RequestParam(name="dia",required=false) Integer dia,
			@RequestParam(name="ente",required=false) Integer ente,
			@RequestParam(name="estado",required=false) String estado, String bonus) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: listado()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = servicioUsuario.buscarPorUsername(user.getUsername());
		if (anio != null)
			this.anioActual=anio;
		if (mes != null)
			this.mesActual=mes;
		if (ente == null)
			enteActual=0;
		else
			enteActual=ente;
		if (estado == null)
			estadoFiltro = "";
		else
			if (estado.equals("todos"))
				estadoFiltro = "";
			else
				estadoFiltro = estado;		
		if (dia == null)
			this.diaFiltro = 0;
		else
			this.diaFiltro = dia;
			
		String fecha = Utilidades.armarFechaMYSQL(diaFiltro, mesActual, anioActual);
		
		List <Servicio> servicios = 
				servicioServicio.listar(anioActual,mesActual,enteActual,estadoFiltro,fecha,usuario.getId_codigo_gestion());
		List <Ente> entes = servicioEnte.listar("");
		ModelAndView mav = new ModelAndView(Vistas.SERVICIOS_LISTADO);
		
		Pageable userPageable = PageRequest.of(page, 60,Sort.by("id"));
		Page<Servicio> servicio = servicioServicio.pageables(enteActual, anioActual, mesActual, fecha, estadoFiltro, userPageable);
		RenderizadorPaginas<Servicio> renderizadorPaginas = new RenderizadorPaginas<Servicio>("/adicional/servicios/listado?anio="+anioActual+"&mes="+mesActual, servicio);
		
		mav.addObject("page", renderizadorPaginas);
		mav.addObject("servicio", servicio);
		mav.addObject(Constante.USUARIO, user.getUsername());
		mav.addObject("modeloServicio", servicios);
		mav.addObject("entes", entes);
		mav.addObject("resultados", servicio.getTotalElements());
		mav.addObject("periodo", mes+"/"+anio);
		informacionFiltro = formatoEnte(enteActual)+" ● "+formatoEstado(estadoFiltro)+" ● "+formatoFecha(diaFiltro);
		mav.addObject("informacionFiltro", informacionFiltro);
		mav.addObject("bonus", bonus);
		mav.addObject("estado", estado);
		return mav;
		}
	
	private String formatoEnte(int numeroEnte) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: formatoEnte()");
		if (numeroEnte == 0)
			return "Todas las entidades";
		else
			return "Entidad: "+servicioEnte.buscarPorId(numeroEnte).getNombre();
	}
	
	private String formatoEstado(String estado) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: formatoEstado()");
		if (estado.equals("")) return "Servicios asignados y no asignados";
		if (estado.equals("Disponible")) return "Sólo servicios disponibles";
		if (estado.equals("Asignado")) return "Sólo servicios asignados";
		return "Error";
	}
	
	private String formatoFecha(Integer fecha) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: formatoFecha()");
		if (fecha == 0) return "Servicios de todo el mes";
		return "Servicios del día "+Utilidades.diaSemana(fecha, mes, anio)+" "+fecha+"/"+mes+"/"+anio;
	}
	
	@GetMapping("/menu")
	public ModelAndView menu() {
		LOG.info("Clase: ControladorServicios. Entrando a Método: menu()");
		
		calcularMes();
		ModelAndView mav = new ModelAndView(Vistas.SERVICIOS_PERIODOS);
		mav.addObject("periodos",servicioPeriodo.listar());
		return mav;
		}
	
	@GetMapping("/proceso")
	public String generarServicios(
			@RequestParam(name="anio",required=true) Integer anio, 
			@RequestParam(name="mes",required=true) Integer mes) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: proceso(). Año: "+anio+", Mes: "+mes);
		this.anio=anio;
		this.mes=mes;
		List<Categoria> categorias = servicioCategoria.listar();
		for (Categoria categoria : categorias)
			this.categ[categoria.getId()] = (float) categoria.getValorHora();
		int diasDelMes = YearMonth.of(anio, mes).lengthOfMonth();
		System.out.println("El mes "+Utilidades.nombreMes(mes)+" tiene "+diasDelMes+" días");
		List<Ente> entes = servicioEnte.entesActivos();
		feriados = this.servicioFeriado.feriadosDelMes(anio,mes);
		//1. RECORRER CADA DÍA DEL MES
		for (int dia=1; dia<=diasDelMes; dia++) {
			if (feriados[dia] == 1)
				esFeriado = true;
			else
				esFeriado = false;
			//2. RECORRER CADA ENTIDAD
			for (Ente ente : entes) 
				//3. RECORRER CADA OBJETIVO
				recorrerObjetivos(dia,ente);
			}
		Periodo modeloPeriodo = servicioPeriodo.buscarPorFecha(anio,mes);
		modeloPeriodo.setGenerado(2);
		servicioPeriodo.nuevo(modeloPeriodo);
		LOG.info("Clase: ControladorServicios. Saliendo de Método: proceso()");
		return "redirect:/gestion/servicios/listado?anio="+anio+"&mes="+mes;
		}
	
	private void recorrerObjetivos(int dia, Ente ente) {
		//LOG.info("Clase: ControladorServicios. Entrando a Método: recorrerObjetivos() Dia: "+dia+" - Ente: "+ente.getNombre());
		List<Objetivo> objetivos = ente.getObjetivos();
		for (Objetivo objetivo : objetivos) 
			//VER QUE EL OBJETIVO ESTÉ ACTIVO Y VALIDAR DÍA DE LA SEMANA Y FERIADOS
			if (objetivo.getEstado().equals("Activo") && comprobarDia(dia,objetivo) && comprobarFeriado(dia,mes,anio,objetivo))
				recorrerGuardias(dia,objetivo);
		
	}
	
	private void recorrerGuardias(int dia, Objetivo objetivo) {
		//LOG.info("Clase: ControladorServicios. Entrando a Método: recorrerGuardias() Dia: "+dia+" - Objetivo: "+objetivo.getDescripcion());
		// RECORRER GUARDIAS
		List<Guardia> guardias = objetivo.getGuardias();
		for (Guardia guardia : guardias) {
			// VER CUANTOS EFECTIVOS SE NECESITAN POR GUARDIA
			for (int j=0; j<objetivo.getEfectivos(); j++) {
				float importeHora = categ[objetivo.getEnte().getCategoria().getId()];
				Servicio mod = new Servicio();
				mod.setAnio(anio);
				String horaEntrada = Utilidades.armarFechaMYSQL(dia,mes,anio)+" "+guardia.getHoraEntrada();
				String horaSalida = Utilidades.armarFechaMYSQL(dia,mes,anio)+" "+guardia.getHoraSalida();
				mod.setFechaHoraEntrada(horaEntrada);
				mod.setFechaHoraSalida(horaSalida);
				mod.setIdObjetivo(objetivo.getId());
				mod.setImporteHora(importeHora);
				mod.setMes(mes);
				servicioServicio.nuevo(mod);
				}
			}
		}
	
	private boolean comprobarFeriado(int dia, int mes, int anio, Objetivo objetivo){
		// Retorna verdadero a menos que sea feriado y ese día no se trabaje en el objetivo.-
		boolean validado= true;
		if (esFeriado && !objetivo.isFeriados())
			validado = false;
		LOG.info("Clase: ControladorServicios. Método: comprobarFeriado(). Fecha: "+dia+"/"+mes+"/"+anio+", Resultado: "+validado);
		return validado;
		}

	private boolean comprobarDia(int dia, Objetivo objetivo) {
		// Retorna verdadero a menos que ese día de la semana (lunes, martes, etc) no se trabaje en el objetivo.-
		String diaSemana = Utilidades.diaSemana(dia, mes, anio);							
		boolean diaValidado = true;
		if (diaSemana.equals("Lunes") && !objetivo.isLunes())
			diaValidado = false;
		if (diaSemana.equals("Martes") && !objetivo.isMartes())
			diaValidado = false;
		if (diaSemana.equals("Miércoles") && !objetivo.isMiercoles())
			diaValidado = false;
		if (diaSemana.equals("Jueves") && !objetivo.isJueves())
			diaValidado = false;
		if (diaSemana.equals("Viernes") && !objetivo.isViernes())
			diaValidado = false;
		if (diaSemana.equals("Sábado") && !objetivo.isSabado())
			diaValidado = false;
		if (diaSemana.equals("Domingo") && !objetivo.isDomingo())
			diaValidado = false;
		//LOG.info("Clase: ControladorServicios. Método: comprobarDia(). Resultado: "+diaValidado+", Día: "+dia+", Objetivo: "+objetivo.toString());
		return diaValidado;
	}
	
	@GetMapping("/asignar")
	public ModelAndView asignar(@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="id",required=false) Integer idServicio, 
			@RequestParam(name="filtro",required=false) String filtro) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: asignar(idServicio: "+idServicio+"), Filtro: "+filtro);
		if (filtro == null) filtro = "";
		if (idServicio != null)
			servicioActual = idServicio;
		ModelAndView mav = new ModelAndView(Vistas.SERVICIOS_ASIGNAR);
		Pageable pageable = PageRequest.of(page, 60,Sort.by("policia.persona.nombre"));
		Page<PersonalHabilitado> modeloPH = servicioPH.pageable(filtro, pageable);
		RenderizadorPaginas<PersonalHabilitado> renderizadorPaginas = new RenderizadorPaginas<PersonalHabilitado>("/gestion/servicios/asignar?id="+idServicio, modeloPH);
		mav.addObject("page", renderizadorPaginas);
		mav.addObject("modeloPH", modeloPH);
		return mav;
		}
	
	@GetMapping("/eliminar")
	public ModelAndView eliminar(@RequestParam(name="id",required=true) int id) {
		try{servicioServicio.eliminar(id);}
		catch(Exception e) {
			LOG.warn("Clase: ControladorServicios. Método: eliminar(idServicio: "+id+"), generó una excepción");
			return this.listado(0, anio,mes, null, null, null, null);
		}
		LOG.info("Clase: ControladorServicios. Método: eliminar(idServicio: "+id+"), se eliminó correctamente");
		return this.listado(0, anio,mes, null, null, null, null);
	}
	
	@GetMapping("/desvincular")
	public ModelAndView desvincular(@RequestParam(name="id",required=true) int id) {
		LOG.info("Clase: ControladorServicios. Entrando a Método: desvincular(id del servicio: "+id+")");
		Servicio modeloServicio = servicioServicio.buscarPorId(id);
		Policia modeloPolicia = servicioPolicia.buscarPorId(modeloServicio.getEfectivo());
		PersonalHabilitado modPH = servicioPH.obtenerPH(modeloPolicia);
		modeloServicio.setEfectivo(null);
		servicioServicio.nuevo(modeloServicio);
		return this.serviciosEfectivo(modPH.getId(),anioActual,mesActual,"desvinculado");
		}
	
	public void calcularMes() {
		LOG.info("Clase: ControladorServicios. Entrando a Método: calcularMes(mes: "+mes+")");
		switch(mes) {
			case 1: nombreMes = "ENERO"; break;
			case 2: nombreMes = "FEBRERO"; break;
			case 3: nombreMes = "MARZO"; break;
			case 4: nombreMes = "ABRIL"; break;
			case 5: nombreMes = "MAYO"; break;
			case 6: nombreMes = "JUNIO"; break;
			case 7: nombreMes = "JULIO"; break;
			case 8: nombreMes = "AGOSTO"; break;
			case 9: nombreMes = "SEPTIEMBRE"; break;
			case 10: nombreMes = "OCTUBRE"; break;
			case 11: nombreMes = "NOVIEMBRE"; break;
			case 12: nombreMes = "DICIEMBRE"; break;
			default: nombreMes = "ERROR"; break;
			}
		}

}
