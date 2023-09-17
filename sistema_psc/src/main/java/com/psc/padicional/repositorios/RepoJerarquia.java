package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Jerarquia;

@Repository("repoJerarquia")
public interface RepoJerarquia extends JpaRepository<Jerarquia,Serializable>, RepoJerarquiaCustom{
	
	public abstract Jerarquia findById(int id);
	
}
