package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Feriado;

public interface ServicioFeriado {
	
	public abstract Feriado nuevo(Feriado feriado);
	
	public abstract List<Feriado> listar();
	
	public abstract Feriado buscarPorId(int id);
	
	public abstract void eliminar(int id);
		
	public abstract Feriado buscarPorFecha(String fecha);
	
	public abstract int[] feriadosDelMes(Integer anio, Integer mes);

}
