package com.psc.padicional.repositorios.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.entidades.QCategoria;
import com.psc.padicional.repositorios.RepoCategoriaCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class RepoCategoriaImpl implements RepoCategoriaCustom{
	
	private QCategoria cat = QCategoria.categoria;
	@PersistenceContext
	private EntityManager em;

	public List<Categoria> obtenerCategorias() {
		JPAQuery<Categoria> query = new JPAQuery<>(em);
		return query.select(cat).from(cat).orderBy(cat.id.asc()).fetch();
	}

	@Override
	public void prueba() {
		// TODO Auto-generated method stub
		
	}

}
