package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Categoria;

public interface ServicioCategoria {
	
	public abstract Categoria nuevo(Categoria categoria);
	
	public abstract List<Categoria> listar();
	
	public abstract Categoria buscarPorId(int id);
	
	public abstract void eliminar(int id);
	
}
