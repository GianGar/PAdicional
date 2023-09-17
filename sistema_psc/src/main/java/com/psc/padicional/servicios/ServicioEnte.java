package com.psc.padicional.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.psc.padicional.entidades.Ente;

public interface ServicioEnte {

	public abstract Ente nuevo(Ente Ente);

	public abstract List<Ente> listar(String filtro);

	public abstract Ente buscarPorId(int id);

	public abstract void eliminar(int id);
	
	public abstract List<Ente> entesActivos();
	
	public abstract Page<Ente> pageables(Integer idCodigo, String filtro, Pageable pageable);
	

}
