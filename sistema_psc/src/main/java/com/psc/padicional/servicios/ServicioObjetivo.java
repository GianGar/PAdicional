package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Objetivo;

public interface ServicioObjetivo {
	
	public abstract Objetivo nuevo(Objetivo objetivo);
	
	public abstract List<Objetivo> listar(int idEnte);
		
	public abstract Objetivo buscarPorId(int id);
	
	public abstract void eliminar(int id);

}
