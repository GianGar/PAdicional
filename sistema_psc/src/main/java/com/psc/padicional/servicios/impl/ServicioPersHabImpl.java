package com.psc.padicional.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.PersonalHabilitado;
import com.psc.padicional.entidades.Policia;
import com.psc.padicional.entidades.QPersonalHabilitado;
import com.psc.padicional.entidades.Servicio;
import com.psc.padicional.repositorios.RepoPersonalHabilitado;
import com.psc.padicional.servicios.ServicioPersHab;
import com.psc.padicional.servicios.ServicioPolicia;
import com.psc.padicional.servicios.ServicioServicio;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service("servicioPersHab")
public class ServicioPersHabImpl implements ServicioPersHab{
	
	@Autowired
	@Qualifier("repoPersonalHab")
	private RepoPersonalHabilitado repoPersonal;
	
	@Autowired
	@Qualifier("servicioServicio")
	private ServicioServicio servicioServicio;
	
	@Autowired
	@Qualifier("servicioPolicia")
	private ServicioPolicia servicioPolicia;

	@Override
	public PersonalHabilitado nuevo(PersonalHabilitado ph) {
		return repoPersonal.save(ph);
		}

	@Override
	public List<PersonalHabilitado> listar(int idCodigo, String filtro) {
		return repoPersonal.listar(idCodigo,filtro);
	}
	
	@Override
	public List<PersonalHabilitado> listarPorDestinoJerarquia() {
		return repoPersonal.listarPorDestinoJerarquia();
	}
	
	@Override
	public PersonalHabilitado buscarPorId(int id) {
		return repoPersonal.findById(id);
	}
	
	@Override
	public void eliminar(int id) {
		PersonalHabilitado ph = buscarPorId(id);
		if (null != ph)
			repoPersonal.delete(ph);	
		}
	
	@Override
	public Policia obtenerPolicia(int id) {
		return servicioPolicia.buscarPorId(id);
	}
	
	@Override
	public Policia obtenerPoliciaConIDPH(int idPH) {
		return servicioPolicia.buscarPorId(idPH);
	}
	
	@Override
	public Page<PersonalHabilitado> pageable(String filtro, Pageable pageable) {
		QPersonalHabilitado root = QPersonalHabilitado.personalHabilitado;
		BooleanExpression query = root.policia.persona.nombre.contains(filtro);
		Page<PersonalHabilitado> personal = repoPersonal.findAll(query, pageable);
		Integer mes = Utilidades.mesProximo();
		Integer anio = Utilidades.anioActual();
		if (mes == 1) anio++;
		for(PersonalHabilitado p : personal) {
			List <Servicio> servicios = servicioServicio.serviciosEfectivo(anio,mes, p.getIdEfectivo());
			Integer horas = Utilidades.cantidadHorasServicios(servicios);
			p.setHorasProximoMes(horas);
			p.setServiciosProximoMes(servicios.size());	
			}
		return personal;
	}



	
	

	@Override
	public PersonalHabilitado obtenerPH(Policia policia) {
		return repoPersonal.obtenerPHconIDPolicia(policia.getId());
	}


	

}
