package com.psc.padicional.repositorios.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.QPersonalHabilitado;
import com.psc.padicional.repositorios.RepoPersonalHabilitadoCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoPersonalHabilitadoImpl implements RepoPersonalHabilitadoCustom{
	
	private QPersonalHabilitado ph = QPersonalHabilitado.personalHabilitado;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PersonalHabilitado> listar(int idCodigo, String filtro) {
		BooleanBuilder condicion = new BooleanBuilder(ph.policia.persona.nombre.contains(filtro));
		Predicate predicado4 = ph.codigoGestion.id.eq(idCodigo);
		condicion.and(predicado4);
		Predicate predicado2 = ph.policia.persona.documento.eq(filtro);
		condicion.or(predicado2);
		if (Utilidades.esNumero(filtro)) 
			{
			Integer numero = Integer.parseInt(filtro);
			Predicate predicado3 = ph.policia.nroAgente.eq(numero);
			condicion.or(predicado3);
			}
		JPAQuery<PersonalHabilitado> query = new JPAQuery<>(em);
		return query.select(ph).from(ph).where(condicion).orderBy(ph.policia.persona.nombre.asc()).fetch();
	}

	@Override
	public List<PersonalHabilitado> listarPorDestinoJerarquia() {
		JPAQuery<PersonalHabilitado> query = new JPAQuery<>(em);
		return query.select(ph).from(ph)
				.orderBy(ph.policia.destino.descripcion.asc())
				.orderBy(ph.policia.jerarquia.id.asc())
				.orderBy(ph.policia.persona.nombre.asc())
				.fetch();
	}

	@Override
	public PersonalHabilitado obtenerPHconIDPolicia(Integer idPolicia) {
		JPAQuery<PersonalHabilitado> query = new JPAQuery<>(em);
		return query.select(ph).from(ph).where(ph.idEfectivo.eq(idPolicia)).fetchOne();
	}

}
