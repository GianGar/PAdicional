package com.psc.padicional.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.psc.padicional.entidades.CodigoGestion;
import com.psc.padicional.repositorios.RepoCodigoGestion;
import com.psc.padicional.servicios.ServicioCodigoGestion;

@Service("servicioCodigoGestion")
public class ServicioCodigoGestionImpl implements ServicioCodigoGestion{
	
	@Autowired
	@Qualifier("repoCodigoGestion")
	private RepoCodigoGestion repoCodigoGestion;

	@Override
	public CodigoGestion nuevo(CodigoGestion cg) {
		return repoCodigoGestion.save(cg);
	}

	@Override
	public List<CodigoGestion> listar() {
		return repoCodigoGestion.findAll();
	}

	@Override
	public CodigoGestion buscarPorId(int id) {
		return repoCodigoGestion.findById(id);
	}

}
