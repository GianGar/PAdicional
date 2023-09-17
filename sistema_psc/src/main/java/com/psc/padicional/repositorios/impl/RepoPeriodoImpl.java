package com.psc.padicional.repositorios.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.entidades.QPeriodo;
import com.psc.padicional.repositorios.RepoPeriodoCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoPeriodoImpl implements RepoPeriodoCustom{
	
	private QPeriodo periodo = QPeriodo.periodo;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Periodo buscarPorFecha(Integer anio, Integer mes) {
		JPAQuery<Periodo> query = new JPAQuery<>(em);
		return query.select(periodo).from(periodo).where(periodo.anio.eq(anio).and(periodo.mes.eq(mes))).fetchOne();
	}
	
	

}
