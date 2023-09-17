package com.psc.padicional.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Periodo;

@Repository("repoPeriodo")
public interface RepoPeriodo extends JpaRepository<Periodo,Serializable>, RepoPeriodoCustom{
	
	public abstract Periodo findById(int id);
	
}
