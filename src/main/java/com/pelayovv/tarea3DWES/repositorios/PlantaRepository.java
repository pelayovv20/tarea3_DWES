package com.pelayovv.tarea3DWES.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository <Planta, Long> {

	Optional<Planta> findById(Long id);
	
	Optional<Planta> findByCodigo(String codigo);
	
	List<Planta> findAllByNombrecomun();
	
	boolean existsByCodigo(String codigo);
	
	
	
	
	
	@Query("UPDATE Planta p SET p.nombrecomun = :nombrecomun WHERE p.codigo = :codigo")
	int modificarNombrecomun(@Param("codigo") String codigo, @Param("nombrecomun") String nombrecomun);
	
	
	
	@Query("UPDATE Planta p SET p.nombrecientifico = :nombrecientifico WHERE p.codigo = :codigo")
	int modificarNombrecientifico(@Param("codigo") String codigo, @Param("nombrecientifico") String nombrecientifico);
	
	
	}
