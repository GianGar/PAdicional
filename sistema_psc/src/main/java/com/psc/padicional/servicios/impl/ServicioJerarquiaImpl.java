package com.psc.padicional.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.psc.padicional.entidades.Jerarquia;
import com.psc.padicional.repositorios.RepoJerarquia;
import com.psc.padicional.servicios.ServicioJerarquia;

@Service("servicioJerarquia")
public class ServicioJerarquiaImpl implements ServicioJerarquia{
	
	@Autowired
	@Qualifier("repoJerarquia")
	private RepoJerarquia repoJerarquia;

	@Override
	public Jerarquia nuevo(Jerarquia cg) {
		return repoJerarquia.save(cg);
	}

	@Override
	public List<Jerarquia> listar() {
		return repoJerarquia.findAll();
	}

	@Override
	public Jerarquia buscarPorId(int id) {
		return repoJerarquia.findById(id);
	}

}
