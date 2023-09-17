package com.psc.padicional.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.psc.padicional.entidades.Policia;
import com.psc.padicional.repositorios.RepoPolicia;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioPolicia;

@Service("servicioPolicia")
public class ServicioPoliciaImpl implements ServicioPolicia{
	
	@Autowired
	@Qualifier("repoPolicia")
	private RepoPolicia repoPolicia;
	
	@Autowired
	@Qualifier("servicioPersHab")
	private ServicioPersHab servicioPH;

	@Override
	public Policia nuevo(Policia policia) {
		return repoPolicia.save(policia);
	}

	@Override
	public List<Policia> listar(String filtro) {
		return repoPolicia.findAll();
	}
	
	@Override
	public List<Policia> listarAptosAdicional(String filtro) {
		List<Policia> listado = new ArrayList<>();
		if (filtro.equals("nulo"))
			return listado;
		else
			return repoPolicia.obtenerAptosAdicional(filtro);
	}

	@Override
	public Policia buscarPorId(int id) {
		return repoPolicia.findById(id);
	}

	@Override
	public void eliminar(int id) {
		Policia policia = buscarPorId(id);
		if (null != policia)
			repoPolicia.delete(policia);	
	}

}
