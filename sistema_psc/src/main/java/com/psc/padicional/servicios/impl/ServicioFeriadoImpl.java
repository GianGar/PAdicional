package com.psc.padicional.servicios.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Feriado;
import com.psc.padicional.repositorios.RepoFeriado;
import com.psc.padicional.servicios.ServicioFeriado;

@Service("servicioFeriado")
public class ServicioFeriadoImpl implements ServicioFeriado{

	@Autowired
	@Qualifier("repoFeriado")
	private RepoFeriado repoFeriado;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Feriado nuevo(Feriado feriado) {
		return repoFeriado.save(feriado);
	}

	@Override
	public List<Feriado> listar() {
		List<Feriado> frds = repoFeriado.feriadosPorFecha();
		
		//Campos calculados
		for (Feriado feriado : frds) {
			feriado.setDiaSemana(Utilidades.diaSemana(feriado.getFecha()));
			feriado.setFechaFormat(Utilidades.formatearFecha(feriado.getFecha()));
			} 
		return frds;
		}

	@Override
	public Feriado buscarPorId(int id) {
		return repoFeriado.findById(id);
		}

	@Override
	public void eliminar(int id) {
		Feriado feriado = buscarPorId(id);
		if (null != feriado)
			repoFeriado.delete(buscarPorId(id));
			}

	@Override
	public Feriado buscarPorFecha(String fecha) {
		return repoFeriado.findByFecha(fecha);
	}

	@Override
	public int[] feriadosDelMes(Integer anio, Integer mes) {
		int [] feriados = new int[32];
		for (int i=1; i<=31; i++)
			feriados[i]=0;
		List<Feriado> mf = repoFeriado.feriadosDelMes(anio,mes);
		for (Feriado f : mf)
			{
			int dia = Integer.parseInt(f.getFecha().substring(5, 7));
			feriados[dia] = 1;
			}
		return feriados;
	}
}


