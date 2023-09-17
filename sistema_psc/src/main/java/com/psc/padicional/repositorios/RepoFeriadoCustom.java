package com.psc.padicional.repositorios;

import java.util.List;

import com.psc.padicional.entidades.Feriado;

public interface RepoFeriadoCustom {
	
	List<Feriado> feriadosPorFecha();
	
	public abstract Feriado findByFecha(String fecha);
	
	public abstract void prueba();
	
	public abstract List<Feriado> feriadosDelMes(Integer anio, Integer mes);

}
