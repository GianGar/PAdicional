package com.psc.padicional.repositorios;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.psc.padicional.entidades.PersonalHabilitado;

@Repository("repoPersonalHab")
public interface RepoPersonalHabilitado extends PagingAndSortingRepository<PersonalHabilitado,Long>,
QuerydslPredicateExecutor<PersonalHabilitado>{
		
	public abstract PersonalHabilitado findById(int id);
	
	public abstract List<PersonalHabilitado> listar(int idCodigo, String filtro);
	
	public abstract List<PersonalHabilitado> listarPorDestinoJerarquia();
	
	PersonalHabilitado obtenerPHconIDPolicia(Integer idPolicia);
	
	
	

}
