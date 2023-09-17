package com.psc.padicional.servicios;
import java.util.List;
import com.psc.padicional.entidades.Destino;

public interface ServicioDestino {
	
	public abstract Destino nuevo(Destino periodo);
	
	public abstract List<Destino> listar();
	
	public abstract Destino buscarPorId(int id);
	
}
