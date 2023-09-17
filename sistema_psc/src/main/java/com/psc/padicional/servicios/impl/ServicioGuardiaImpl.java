package com.psc.padicional.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Guardia;
import com.psc.padicional.repositorios.RepoGuardia;
import com.psc.padicional.servicios.ServicioGuardia;

@Service("servicioGuardia")
public class ServicioGuardiaImpl implements ServicioGuardia{
	
	@Autowired
	@Qualifier("repoGuardia")
	private RepoGuardia repoGuardia;

	@Override
	public Guardia nueva(Guardia guardia) {
		return repoGuardia.save(guardia);
	}

	@Override
	public List<Guardia> listar(int idObjetivo) {
		List<Guardia> guardias = repoGuardia.guardiasDelObjetivo(idObjetivo);
		for(Guardia guardia : guardias) 
			guardia.setDuracion(Utilidades.getDuracionGuardia(guardia.getHoraEntrada(), guardia.getHoraSalida()));
		return guardias;
	}

	@Override
	public Guardia buscarPorId(int id) {
		return repoGuardia.findById(id);
	}

	@Override
	public void eliminar(int id) {
		Guardia guardia = buscarPorId(id);
		if (null != guardia) 
			{
			repoGuardia.delete(buscarPorId(id));
			}
		
	}

}
