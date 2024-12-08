package com.pelayovv.tarea3DWES.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Ejemplar;

import jakarta.transaction.Transactional;

@Repository
public interface EjemplarRepository extends JpaRepository <Ejemplar, Long> {

	@Transactional
	@Modifying
	@Query("UPDATE Ejemplar e SET e.nombre = :nombre WHERE e.id = :idejemplar")
	int cambiarNombreEjemplar(@Param("idejemplar") Long idejemplar, @Param("nombre") String nombre);
	
	@Query("SELECT e FROM Ejemplar e WHERE e.planta.codigo = :codigoplanta")
	List<Ejemplar> ejemplaresPorTipoPlanta(@Param("codigoplanta") String codigoPlanta);
}
