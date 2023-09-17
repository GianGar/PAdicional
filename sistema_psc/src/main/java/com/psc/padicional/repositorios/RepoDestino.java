package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Destino;

@Repository("repoDestino")
public interface RepoDestino extends JpaRepository<Destino,Serializable>, RepoDestinoCustom{
	
	public abstract Destino findById(int id);
	
}
