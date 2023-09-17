package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psc.padicional.entidades.Policia;

@Repository("repoPolicia")
public interface RepoPolicia extends JpaRepository<Policia,Serializable>,RepoPoliciaCustom{

	public abstract Policia findById(int id);

}
