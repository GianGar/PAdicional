package com.psc.padicional.servicios;

import java.util.List;
import com.psc.padicional.entidades.Policia;

public interface ServicioPolicia {
	
	public abstract Policia nuevo(Policia policia);

	public abstract List<Policia> listar(String filtro);
	
	public abstract List<Policia> listarAptosAdicional(String filtro);

	public abstract Policia buscarPorId(int id);

	public abstract void eliminar(int id);

}
