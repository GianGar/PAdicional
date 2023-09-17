package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.CodigoGestion;

@Repository("repoCodigoGestion")
public interface RepoCodigoGestion extends JpaRepository<CodigoGestion,Serializable>, RepoCodigoGestionCustom{
	
	public abstract CodigoGestion findById(int id);
	
}
