package com.psc.padicional.servicios;
import java.util.List;
import com.psc.padicional.entidades.Suspension;

public interface ServicioSuspension {
	
	public abstract Suspension nuevo(Suspension periodo);
	
	public abstract List<Suspension> listar();
	
	public abstract Suspension buscarPorId(int id);
	
	public abstract List<Suspension> suspensionesDe(int idPH);
	
}
