package com.psc.padicional.repositorios;

import com.psc.padicional.entidades.Periodo;

public interface RepoPeriodoCustom {
	
	Periodo buscarPorFecha(Integer anio, Integer mes);

}
