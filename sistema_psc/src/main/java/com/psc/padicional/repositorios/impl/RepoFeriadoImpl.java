package com.psc.padicional.repositorios.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Feriado;
import com.psc.padicional.entidades.QFeriado;
import com.psc.padicional.repositorios.RepoFeriadoCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoFeriadoImpl implements RepoFeriadoCustom{
	
	private QFeriado feriado = QFeriado.feriado;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Feriado> feriadosPorFecha() {
		JPAQuery<Feriado> query = new JPAQuery<>(em);
		return query.select(feriado).from(feriado).orderBy(feriado.fecha.asc()).fetch();
		}

	@Override
	public Feriado findByFecha(String fecha) {
		JPAQuery<Feriado> query = new JPAQuery<>(em);
		return query.select(feriado).from(feriado).where(feriado.fecha.eq(fecha)).fetchOne();
	}

	@Override
	public List<Feriado> feriadosDelMes(Integer anio, Integer mes) {
		JPAQuery<Feriado> query = new JPAQuery<>(em);
		return query.select(feriado).from(feriado).where(feriado.fecha.startsWith(Utilidades.formatoFechaSQL(anio, mes))).fetch();
		
	}

	@Override
	public void prueba() {
		// TODO Auto-generated method stub
		
	}
	

}
