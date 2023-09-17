package com.psc.padicional.repositorios;

import java.util.List;

import com.psc.padicional.entidades.PersonalHabilitado;

public interface RepoPersonalHabilitadoCustom {
	
	List<PersonalHabilitado> listar(int idCodigo, String filtro);
	
	List<PersonalHabilitado> listarPorDestinoJerarquia();
	
	PersonalHabilitado obtenerPHconIDPolicia(Integer idPolicia);

}
