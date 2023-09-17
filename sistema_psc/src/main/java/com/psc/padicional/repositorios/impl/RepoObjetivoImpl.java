package com.psc.padicional.repositorios.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.psc.padicional.entidades.Objetivo;
import com.psc.padicional.entidades.QObjetivo;
import com.psc.padicional.repositorios.RepoObjetivoCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoObjetivoImpl implements RepoObjetivoCustom {
	
	private QObjetivo objetivo = QObjetivo.objetivo;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Objetivo> objetivosDelEnte(int idEnte) {
		JPAQuery<Objetivo> query = new JPAQuery<>(em);
		return query.select(objetivo).from(objetivo).
				where(objetivo.idEnte.eq(idEnte)).orderBy(objetivo.estado.asc()).orderBy(objetivo.descripcion.asc()).fetch();
	}

}
