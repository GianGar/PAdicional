package com.psc.padicional.repositorios;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.psc.padicional.entidades.Log;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log,Serializable>{
	
	
	
}
