package com.psc.padicional.servicios;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Policia;

public interface ServicioPersHab {
	
	public abstract PersonalHabilitado nuevo(PersonalHabilitado ph);

	public abstract List<PersonalHabilitado> listar(int idCodigo, String filtro);
	
	public abstract List<PersonalHabilitado> listarPorDestinoJerarquia();

	public abstract PersonalHabilitado buscarPorId(int id);

	public abstract void eliminar(int id);
		
	public abstract Policia obtenerPolicia(int id);
	
	public abstract Policia obtenerPoliciaConIDPH(int idPH);
	
	public abstract PersonalHabilitado obtenerPH(Policia policia);
	
	public abstract Page<PersonalHabilitado> pageable(String filtro, Pageable pageable);

}
