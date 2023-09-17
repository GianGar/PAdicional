package com.psc.padicional.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.entidades.Objetivo;
import com.psc.padicional.repositorios.RepoObjetivo;
import com.psc.padicional.servicios.ServicioObjetivo;

@Service("servicioObjetivo")
public class ServicioObjetivoImpl implements ServicioObjetivo{
	
	@Autowired
	@Qualifier("repoObjetivo")
	private RepoObjetivo repoObjetivo;
	
	@Override
	public List<Objetivo> listar(int idEnte){
		return repoObjetivo.objetivosDelEnte(idEnte);
	}

	@Override
	public Objetivo buscarPorId(int id) {
		return repoObjetivo.findById(id);
	}

	@Override
	public Objetivo nuevo(Objetivo objetivo) {
		return repoObjetivo.save(objetivo);
	}


	@Override
	public void eliminar(int id) {
		Objetivo objetivo = buscarPorId(id);
		if (null != objetivo) 
			{
			repoObjetivo.delete(objetivo);
			}
		else
			System.out.println("Objetivo nulo , no se puede borrar...?");
		}



}
