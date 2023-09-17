package com.psc.padicional.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.entidades.Periodo;
import com.psc.padicional.repositorios.RepoPeriodo;
import com.psc.padicional.servicios.ServicioPeriodo;

@Service("servicioPeriodo")
public class ServicioPeriodoImpl implements ServicioPeriodo{
	
	@Autowired
	@Qualifier("repoPeriodo")
	private RepoPeriodo repoPeriodo;

	@Override
	public Periodo nuevo(Periodo periodo) {
		return repoPeriodo.save(periodo);
	}

	@Override
	public List<Periodo> listar() {
		List<Periodo> periodos = repoPeriodo.findAll();
		for (Periodo p : periodos)
			p.setDescripcion(p.getAnio()+" - "+Utilidades.nombreMes(p.getMes()));
		return periodos;
	}

	@Override
	public Periodo buscarPorId(int id) {
		return repoPeriodo.findById(id);
	}
	
	@Override
	public void eliminar(int id) {
		Periodo cat = buscarPorId(id);
		if (null != cat)
			repoPeriodo.delete(cat);
		
	}

	@Override
	public Periodo buscarPorFecha(Integer anio, Integer mes) {
		return repoPeriodo.buscarPorFecha(anio, mes);
	}

}
