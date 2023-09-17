package com.psc.padicional.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.QServicio;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.repositorios.RepoServicio;
import com.psc.padicional.repositorios.impl.RepoServicioImpl;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioPolicia;
import com.psc.padicional.servicios.ServicioServicio;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service("servicioServicio")
public class ServicioServicioImpl implements ServicioServicio{
		
	@Autowired
	@Qualifier("repoServicio")
	private RepoServicio repoServicio;
	
	@Autowired
	@Qualifier("servicioPersHab")
	private ServicioPersHab servicioPH;
	
	@Autowired
	@Qualifier("servicioPolicia")
	private ServicioPolicia servicioPolicia;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void eliminar(int id) {
		Servicio servicio = repoServicio.findById(id);
		if (null != servicio)
			repoServicio.delete(servicio);		
	}

	@Override
	public List<Servicio> listar(Integer anio, Integer mes, Integer ente, String estado, String fecha, Integer idCodigo) {
		return repoServicio.listado(anio, mes, ente, estado, fecha, idCodigo);
		}

	@Override
	public Servicio nuevo(Servicio servicio) {
		return repoServicio.save(servicio);
	}

	@Override
	public Servicio buscarPorId(int id) {
		return repoServicio.findById(id);
		}

	@Override
	public List<Servicio> serviciosEfectivo(Integer anio, Integer mes, Integer efectivo) {
		List<Servicio> srvs = repoServicio.serviciosDelEfectivo(anio,mes, efectivo);
		List<Servicio> modeloServicio = new ArrayList<>();
		int cont = 1;
		for (Servicio servicio : srvs)
			{
			servicio.setNumeroListado(cont);
			modeloServicio.add(servicio);
			cont++;
			}	
		return modeloServicio;
		}

	@Override
	public List<Servicio> serviciosDisponibles
	(Integer anio, Integer mes, PersonalHabilitado persona, Integer ente, String fecha) {
		//LOG.info("Clase: ServicioServicioImpl. Entrando a Método: serviciosDisponibles(). Año: "+anio+", Mes: "+mes+", Persona: "+persona.toString()+", Ente: "+ente+", Fecha: "+fecha);
		return repoServicio.serviciosDisponibles(anio,mes, persona, ente, fecha);
		}

	@Override
	public List<Ente> entesConServiciosDisponibles(Integer anio, Integer mes, PersonalHabilitado persona) {
		//1. Obtengo todos los servicios que puede realizar una persona en determinado período.
		Iterable<Servicio> srvs = repoServicio.serviciosDisponibles(anio,mes, persona, 0, null);
		List<Ente> entes = new ArrayList<>();
		//2. Obtengo todos los entes verificando que no se repitan.
		for (Servicio servicio : srvs)
			if (!entes.contains(servicio.getObjetivo().getEnte()))
				entes.add(servicio.getObjetivo().getEnte());
		//3. Retorno.
		return entes;
	}
	
	@Override
	public Page<Servicio> pageables(Integer ente, Integer anio, Integer mes, String fecha, String estado, Pageable pageable) {
		if (fecha == null)
			fecha = "";
		if (estado.equals("todos"))
			estado = "";
		QServicio servicio = QServicio.servicio;
		//1. Corroborar año y mes.
		BooleanExpression query = servicio.anio.eq(anio).and(servicio.mes.eq(mes));
		//2. Corroborar fecha.
		query = query.and(servicio.fechaHoraEntrada.startsWith(fecha));
		//3. Corroborar ente.
		if (ente != 0)
			query = query.and(servicio.objetivo.idEnte.eq(ente));
		//4. Corroborar estado.
		if (estado.equals("Disponible"))
			query = query.and(servicio.efectivo.isNull());
		if (estado.equals("Asignado"))
			query = query.and(servicio.efectivo.isNotNull());
		return repoServicio.findAll(query, pageable);
	}


	
	}
