package com.psc.padicional.repositorios.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Policia;
import com.psc.padicional.entidades.QPolicia;
import com.psc.padicional.repositorios.RepoPoliciaCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoPoliciaImpl implements RepoPoliciaCustom {
	
	private QPolicia policia = QPolicia.policia;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Policia> obtenerAptosAdicional(String filtro) {
		BooleanBuilder condicion = new BooleanBuilder(policia.persona.nombre.contains(filtro));
		Predicate predicado2 = policia.persona.documento.eq(filtro);
		condicion.or(predicado2);
		if (Utilidades.esNumero(filtro)) 
			{
			Integer numero = Integer.parseInt(filtro);
			Predicate predicado3 = policia.nroAgente.eq(numero);
			condicion.or(predicado3);
			}
		Predicate predicado4 = policia.idJerarquia.between(406, 417);
		condicion.and(predicado4);
		JPAQuery<Policia> query = new JPAQuery<>(em);
		return query.select(policia).from(policia).where(condicion).orderBy(policia.persona.nombre.asc()).limit(10).fetch();
	}




}
