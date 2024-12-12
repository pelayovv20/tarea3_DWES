package com.pelayovv.tarea3DWES.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Planta;

import jakarta.transaction.Transactional;

@Repository
public interface PlantaRepository extends JpaRepository <Planta, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Planta p SET p.nombrecomun = :nombrecomun WHERE p.codigo = :codigo")
	boolean modificarNombrecomun(@Param("codigo") String codigo, @Param("nombrecomun") String nombrecomun);
	
	@Transactional
	@Modifying
	@Query("UPDATE Planta p SET p.nombrecientifico = :nombrecientifico WHERE p.codigo = :codigo")
	boolean modificarNombrecientifico(@Param("codigo") String codigo, @Param("nombrecientifico") String nombrecientifico);
	
	Optional<Planta> findByCodigo(String codigo);
	
	
	boolean existsByCodigo(String codigo);
	
	}
