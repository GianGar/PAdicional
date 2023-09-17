package com.psc.padicional.repositorios;

import java.util.List;

import com.psc.padicional.entidades.Ente;

public interface RepoEnteCustom {
	
	public List<Ente> obtenerEntes(String filtro);
		
	public List<Ente> obtenerEntesActivos();
	
}
