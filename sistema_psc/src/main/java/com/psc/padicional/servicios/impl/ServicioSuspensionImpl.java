package com.psc.padicional.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Suspension;
import com.psc.padicional.repositorios.RepoSuspension;
import com.psc.padicional.servicios.ServicioSuspension;

@Service("servicioSuspension")
public class ServicioSuspensionImpl implements ServicioSuspension{
	
	@Autowired
	@Qualifier("repoSuspension")
	private RepoSuspension repoSuspension;

	@Override
	public Suspension nuevo(Suspension cg) {
		return repoSuspension.save(cg);
	}

	@Override
	public List<Suspension> listar() {
		List<Suspension> susp = repoSuspension.findAll();
		for (Suspension s : susp)
			{
			s.setFechaInicioFormat(Utilidades.formatearFecha(s.getFecha_inicio()));;
			s.setFechaFinFormat(Utilidades.formatearFecha(s.getFecha_fin()));;
			}
		return susp;
	}

	@Override
	public Suspension buscarPorId(int id) {
		Suspension susp = repoSuspension.findById(id);
		susp.setFechaInicioFormat(Utilidades.formatearFecha(susp.getFecha_inicio()));;
		susp.setFechaFinFormat(Utilidades.formatearFecha(susp.getFecha_fin()));;
		return susp;
	}
	
	public List<Suspension> suspensionesDe(int idPH){
		List<Suspension> all = repoSuspension.findAll();
		List<Suspension> suspensiones = new ArrayList<Suspension>();
		for (Suspension s : all)
			if (s.getIdPH()==idPH)
				{
				s.setFechaInicioFormat(Utilidades.formatearFecha(s.getFecha_inicio()));;
				s.setFechaFinFormat(Utilidades.formatearFecha(s.getFecha_fin()));;
				suspensiones.add(s);
				}
		return suspensiones;
	}

}
