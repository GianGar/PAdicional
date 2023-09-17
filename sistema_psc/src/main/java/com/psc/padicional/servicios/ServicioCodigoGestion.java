package com.psc.padicional.servicios;
import java.util.List;
import com.psc.padicional.entidades.CodigoGestion;

public interface ServicioCodigoGestion {
	
	public abstract CodigoGestion nuevo(CodigoGestion periodo);
	
	public abstract List<CodigoGestion> listar();
	
	public abstract CodigoGestion buscarPorId(int id);
	
}
