package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Usuario;

public interface ServicioUsuario {
	
	public abstract Usuario nuevo(Usuario user);
	
	public abstract List<Usuario> listar();
	
	public abstract Usuario buscarPorUsername(String username);
					
}
