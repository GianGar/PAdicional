package com.psc.padicional.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psc.padicional.entidades.Suspension;

@Repository("repoSuspension")
public interface RepoSuspension extends JpaRepository<Suspension,Serializable>, RepoSuspensionCustom{
	
	public abstract Suspension findById(int id);
	
}
