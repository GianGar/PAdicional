package com.psc.padicional.repositorios;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Objetivo;

@Repository("repoObjetivo")
public interface RepoObjetivo extends JpaRepository<Objetivo,Serializable> {
	
	public List<Objetivo> objetivosDelEnte(int idEnte);
	
	public Objetivo findById(int id);
	
}
