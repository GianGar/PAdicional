package com.psc.padicional.repositorios.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.QEnte;
import com.psc.padicional.repositorios.RepoEnteCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoEnteImpl implements RepoEnteCustom{
	
	private QEnte ente = QEnte.ente;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Ente> obtenerEntes(String filtro) {
		JPAQuery<Ente> query = new JPAQuery<>(em);
		return query.select(ente).from(ente).
				where(ente.nombre.contains(filtro)).
				orderBy(ente.estado.asc()).orderBy(ente.nombre.asc()).fetch();
		}
	
	public List<Ente> obtenerEntesActivos() {
		JPAQuery<Ente> query = new JPAQuery<>(em);
		return query.select(ente).from(ente).where(ente.estado.eq("Activo")).
				orderBy(ente.estado.asc()).orderBy(ente.nombre.asc()).fetch();
		}



}
