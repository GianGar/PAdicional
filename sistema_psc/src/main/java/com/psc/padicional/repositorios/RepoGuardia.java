package com.psc.padicional.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Guardia;

@Repository("repoGuardia")

public interface RepoGuardia extends JpaRepository<Guardia,Serializable>{
	
	public List<Guardia> guardiasDelObjetivo(int idObjetivo);
	
	public abstract Guardia findById(int id);

}
