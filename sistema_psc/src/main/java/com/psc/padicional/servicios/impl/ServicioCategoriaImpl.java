package com.psc.padicional.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.psc.padicional.componentes.Utilidades;
import com.psc.padicional.entidades.Categoria;
import com.psc.padicional.repositorios.RepoCategoria;
import com.psc.padicional.servicios.ServicioCategoria;

@Service("servicioCategoria")
public class ServicioCategoriaImpl implements ServicioCategoria{
	
	@Autowired
	@Qualifier("repoCategoria")
	private RepoCategoria repoCategoria;

	@Override
	public Categoria nuevo(Categoria categoria) {
		return repoCategoria.save(categoria);
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria> cats = repoCategoria.findAll();
		for(Categoria c : cats) 
			c.setValorHoraFormat(""+Utilidades.redondear(c.getValorHora(), 2));
		return cats;
	}

	@Override
	public Categoria buscarPorId(int id) {
		return repoCategoria.findById(id);
	}

	@Override
	public void eliminar(int id) {
		Categoria cat = buscarPorId(id);
		if (null != cat)
			repoCategoria.delete(cat);
		
	}

}
