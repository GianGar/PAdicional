package com.psc.padicional.servicios.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.psc.padicional.entidades.Usuario;
import com.psc.padicional.repositorios.RepoUsuario;
import com.psc.padicional.servicios.ServicioUsuario;

@Service("servicioUsuario")
public class ServicioUsuarioImpl implements ServicioUsuario{

	@Autowired
	@Qualifier("userRepository")
	private RepoUsuario repoUsuario;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Usuario nuevo(Usuario user) {
		return repoUsuario.save(user);
	}

	@Override
	public List<Usuario> listar() {
		return repoUsuario.findAll();
		}

	@Override
	public Usuario buscarPorUsername(String username) {
		return repoUsuario.findByUsername(username);
	}


}


