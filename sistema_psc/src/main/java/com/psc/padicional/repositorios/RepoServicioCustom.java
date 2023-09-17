package com.psc.padicional.repositorios;

import java.util.List;

import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Servicio;

public interface RepoServicioCustom {
	
	public abstract List<Servicio> findByEfectivo(int efectivo);
	
	public abstract List<Servicio> findByFecha(String fecha);
	
	public abstract List<Servicio> listado(Integer anio, Integer mes, Integer ente, String estado, String fecha, Integer idCodigo);
	
	public abstract List<Servicio> serviciosDelEfectivo(Integer anio, Integer mes, Integer efectivo);
	
	public abstract List<Servicio> serviciosDisponibles
	(Integer anio, Integer mes, PersonalHabilitado persona, Integer ente, String fecha);

}
