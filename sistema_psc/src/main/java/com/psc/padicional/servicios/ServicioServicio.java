package com.psc.padicional.servicios;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.psc.padicional.entidades.Ente;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Servicio;

public interface ServicioServicio {
	
	public abstract Servicio nuevo(Servicio servicio);
	
	public abstract List<Servicio> listar(Integer mes, Integer anio, Integer ente, String estado, String fecha, Integer idCodigo);
	
	public abstract Servicio buscarPorId(int id);
	
	public abstract List<Servicio> serviciosEfectivo(Integer mes, Integer anio, Integer efectivo);
	
	public abstract List<Servicio> serviciosDisponibles
	(Integer mes, Integer anio, PersonalHabilitado persona, Integer ente, String fecha);
	
	public abstract List<Ente> entesConServiciosDisponibles(Integer mes, Integer anio, PersonalHabilitado persona);
	
	public abstract Page<Servicio> pageables(Integer ente, Integer mes, Integer anio, String fecha, String estado, Pageable pageable);
	
	public abstract void eliminar(int id);


}
