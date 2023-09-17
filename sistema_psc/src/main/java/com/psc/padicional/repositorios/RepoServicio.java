package com.psc.padicional.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psc.padicional.entidades.Servicio;
import com.querydsl.core.types.Predicate;

@Repository("repoServicio")
public interface RepoServicio extends PagingAndSortingRepository<Servicio,Long>,RepoServicioCustom,
QuerydslPredicateExecutor<Servicio>{
	
	public abstract Servicio findById(int id);
	
	public abstract List<Servicio> findByEfectivo(int efectivo);
	
	public abstract Page<Servicio> findAll(Predicate predicate, Pageable pageable);
	
	@Query(value = 
			"SELECT * FROM pa_servicios "
			+ "WHERE pa_servicios.fechaHoraEntrada LIKE :periodo% "
			+ "AND pa_servicios.fechaHoraEntrada LIKE :fecha% "
			+ "AND pa_servicios.estado LIKE :estado% "
			+ "", nativeQuery = true)
	public abstract Page<Servicio> findServiciosTodos(
			@Param("periodo") String periodo, 
			@Param("fecha") String fecha, 
			@Param("estado") String estado, Pageable pageable);		
	

}
