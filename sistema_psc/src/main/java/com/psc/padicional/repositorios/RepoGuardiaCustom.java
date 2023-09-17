package com.psc.padicional.repositorios;

import java.util.List;

import com.psc.padicional.entidades.Guardia;

public interface RepoGuardiaCustom {
	
	List<Guardia> guardiasDelObjetivo(int idObjetivo);

}
