package com.psc.padicional.repositorios;

import java.util.List;
import com.psc.padicional.entidades.Policia;

public interface RepoPoliciaCustom {
	
	List<Policia> obtenerAptosAdicional(String filtro);
		

}
