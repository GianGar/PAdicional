package com.psc.padicional.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.entidades.Destino;
import com.psc.padicional.repositorios.RepoDestino;
import com.psc.padicional.servicios.ServicioDestino;

@Service("servicioDestino")
public class ServicioDestinoImpl implements ServicioDestino{
	
	@Autowired
	@Qualifier("repoDestino")
	private RepoDestino repoDestino;

	@Override
	public Destino nuevo(Destino cg) {
		return repoDestino.save(cg);
	}

	@Override
	public List<Destino> listar() {
		return repoDestino.findAll();
	}

	@Override
	public Destino buscarPorId(int id) {
		return repoDestino.findById(id);
	}

}
