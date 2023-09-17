package com.psc.padicional.repositorios;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Categoria;

@Repository("repoCategoria")
public interface RepoCategoria extends JpaRepository<Categoria,Serializable>,RepoCategoriaCustom{
	
	public abstract Categoria findById(int id);
	public abstract List<Categoria> findAllByOrderByIdAsc();

}
