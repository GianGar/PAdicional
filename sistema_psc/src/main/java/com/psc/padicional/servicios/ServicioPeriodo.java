package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Periodo;

public interface ServicioPeriodo {
	
	public abstract Periodo nuevo(Periodo periodo);
	
	public abstract List<Periodo> listar();
	
	public abstract Periodo buscarPorId(int id);
		
	public abstract void eliminar(int id);
	
	public abstract Periodo buscarPorFecha(Integer anio, Integer mes);

}
