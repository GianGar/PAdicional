package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Feriado;

@Repository("repoFeriado")
public interface RepoFeriado extends JpaRepository<Feriado, Serializable>,RepoFeriadoCustom{
	
	public abstract Feriado findByFecha(String fecha);
	
	public abstract Feriado findById(int id);
	
	
	
	
	

}
