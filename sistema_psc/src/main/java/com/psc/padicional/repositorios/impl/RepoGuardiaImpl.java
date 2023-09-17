package com.psc.padicional.repositorios.impl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.psc.padicional.entidades.Guardia;
import com.psc.padicional.entidades.QGuardia;
import com.psc.padicional.repositorios.RepoGuardiaCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoGuardiaImpl implements RepoGuardiaCustom{
	
	private QGuardia guardia = QGuardia.guardia;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Guardia> guardiasDelObjetivo(int objetivo) {
		JPAQuery<Guardia> query = new JPAQuery<>(em);
		return query.select(guardia).from(guardia).where(guardia.idObjetivo.eq(objetivo)).fetch();
	}
}
