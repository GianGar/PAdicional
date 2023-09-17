package com.psc.padicional.servicios.impl;

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
import com.psc.padicional.repositorios.RepoEnte;
import com.psc.padicional.servicios.ServicioEnte;

@Service("servicioEnte")
public class ServicioEnteImpl implements ServicioEnte {
	
	private static final Log LOG = LogFactory.getLog(ServicioEnteImpl.class);
	
	@Autowired
	@Qualifier("repositorioEnte")
	private RepoEnte repoEnte;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Ente nuevo(Ente ente) {
		Ente nuevo;
		try{
		nuevo = repoEnte.save(ente);
			}
		catch(Exception e) {
			return null;
			}
		return nuevo;
	}

	public List<Ente> listar(String filtro) {
		LOG.info("Clase: ServicioEnteImpl. Entrando a Método: listar() - filtro: "+filtro);
		return repoEnte.obtenerEntes(filtro);
	}

	
	@Override
	public Ente buscarPorId(int id) {
		LOG.info("Clase: ServicioEnteImpl. Entrando a Método: buscarporID() - id: "+id);
		return repoEnte.findById(id);
	}

	@Override
	public void eliminar(int id) {
		LOG.info("Clase: ServicioEnteImpl. Entrando a Método: eliminar() - id: "+id);
		Ente ente = buscarPorId(id);
		if (null != ente)
			repoEnte.delete(ente);		
	}

	@Override
	public List<Ente> entesActivos() {
		LOG.info("Clase: ServicioEnteImpl. Entrando a Método: entesActivos()");
		return repoEnte.obtenerEntesActivos();
	}

	@Override
	public Page<Ente> pageables(Integer idCodigo, String filtro, Pageable pageable) {
		return repoEnte.findEntes(idCodigo,filtro, pageable);
	}

}
