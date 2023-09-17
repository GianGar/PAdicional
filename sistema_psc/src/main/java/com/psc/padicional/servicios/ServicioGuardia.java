package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Guardia;

public interface ServicioGuardia {
	
	public abstract Guardia nueva(Guardia guardia);
	
	public abstract List<Guardia> listar(int idObjetivo);
		
	public abstract Guardia buscarPorId(int id);
	
	public abstract void eliminar(int id);	

}
