package com.psc.padicional.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Usuario;

@Repository("userRepository")
public interface RepoUsuario extends JpaRepository<Usuario, Serializable>{
	
	public abstract Usuario findByUsername(String username);
	
}
