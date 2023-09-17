package com.psc.padicional.repositorios;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Ente;

@Repository("repositorioEnte")
public interface RepoEnte extends PagingAndSortingRepository<Ente,Serializable>, RepoEnteCustom{
	
	public abstract Ente findById(int id);
		
	@Query(value = 
			"SELECT * FROM pa_entes "
			+ "WHERE pa_entes.nombre LIKE %:filtro% AND pa_entes.id_codigo_gestion = :idCodigo"
			+ "", nativeQuery = true)
	public abstract Page<Ente> findEntes(
			@Param("idCodigo") Integer idCodigo,
			@Param("filtro") String filtro, Pageable pageable);
			
	
	
			
}
