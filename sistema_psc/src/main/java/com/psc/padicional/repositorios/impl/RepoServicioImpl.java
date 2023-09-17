package com.psc.padicional.repositorios.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.psc.padicional.controladores.ControladorPersonal;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.QServicio;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.repositorios.RepoServicioCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoServicioImpl implements RepoServicioCustom {
	
	private QServicio servicio = QServicio.servicio;
	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Servicio> findByEfectivo(int efectivo) {
		JPAQuery<Servicio> query = new JPAQuery<>(em);
		return query.select(servicio).from(servicio).where(servicio.efectivo.eq(efectivo)).fetch();
	}
	
	@Override
	public List<Servicio> findByFecha(String fecha) {
		JPAQuery<Servicio> query = new JPAQuery<>(em);
		return query.select(servicio).from(servicio).where(servicio.fechaHoraEntrada.contains(fecha)).fetch();
	}

	@Override
	public List<Servicio> listado(Integer anio, Integer mes, Integer ente, String estado, String fecha, Integer idCodigo) {
		BooleanBuilder where = new BooleanBuilder(servicio.anio.eq(anio).and(servicio.mes.eq(mes)));
		if (ente != 0) 
			{
			Predicate predicate2 = servicio.objetivo.idEnte.eq(ente);
			where.and(predicate2);
			}
		if (estado.equals("disponibles"))
			{
			Predicate predicate3 = servicio.efectivo.isNull();
			where.and(predicate3);
			}
		if (estado.equals("asignados"))
			{
			Predicate predicate4 = servicio.efectivo.isNotNull();
			where.and(predicate4);
			}
		if (fecha != null) 
			{
			Predicate predicate5 = servicio.fechaHoraEntrada.startsWith(fecha);
			where.and(predicate5);
			}
		if (idCodigo != null) 
			{
			Predicate predicate6 = servicio.objetivo.ente.id_codigo_gestion.eq(idCodigo);
			where.and(predicate6);
			}
		JPAQuery<Servicio> query = new JPAQuery<>(em);
		return query.select(servicio).from(servicio).where(where).fetch();
	}

	@Override
	public List<Servicio> serviciosDelEfectivo(Integer anio, Integer mes, Integer efectivo) {
		//Recibe un período formateado (Ej: 2019-01)
		BooleanBuilder where = new BooleanBuilder(servicio.anio.eq(anio).and(servicio.mes.eq(mes)));
		Predicate predicate2 = servicio.efectivo.eq(efectivo);
		where.and(predicate2);
		JPAQuery<Servicio> query = new JPAQuery<>(em);
		return query.select(servicio).from(servicio).where(where).fetch();
	}

	@Override
	public List<Servicio> serviciosDisponibles
	(Integer anio, Integer mes, PersonalHabilitado persona, Integer ente, String fecha) {
		//LOG.info("Clase: RepoServicio. Entrando a Método: serviciosDisponibles() Ente: "+ente+", Fecha: "+fecha+", PH: "+persona.toString());
		BooleanBuilder where = new BooleanBuilder(servicio.anio.eq(anio).and(servicio.mes.eq(mes)));
		
		//Verificar si está disponible.
		where.and(servicio.efectivo.isNull());		
		
		if (!persona.isArma())
			where.and(servicio.objetivo.arma.eq(persona.isArma()));
		if (!persona.isCivilFormal())
			where.and(servicio.objetivo.civilFormal.eq(persona.isCivilFormal()));
		if (!persona.isTraje())
			where.and(servicio.objetivo.traje.eq(persona.isTraje()));
		if (!persona.isUniforme())
			where.and(servicio.objetivo.uniforme.eq(persona.isUniforme()));
		if (ente != 0) 
			where.and(servicio.objetivo.idEnte.eq(ente));
		if (fecha != null) 
			where.and(servicio.fechaHoraEntrada.startsWith(fecha));
		
		JPAQuery<Servicio> query = new JPAQuery<>(em);
		return query.select(servicio).
				from(servicio).where(where).
				orderBy(servicio.fechaHoraEntrada.asc()).
				orderBy(servicio.objetivo.ente.nombre.asc()).fetch();
	
	

	}


	

}
