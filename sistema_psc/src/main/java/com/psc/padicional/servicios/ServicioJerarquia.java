package com.psc.padicional.servicios;
import java.util.List;
import com.psc.padicional.entidades.Jerarquia;

public interface ServicioJerarquia {
	
	public abstract Jerarquia nuevo(Jerarquia periodo);
	
	public abstract List<Jerarquia> listar();
	
	public abstract Jerarquia buscarPorId(int id);
	
}
