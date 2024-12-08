package com.pelayovv.tarea3DWES.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

	
	@Query("SELECT c.persona.id FROM Credenciales c WHERE c.usuario = :usuario")
	Long personaAutenticada(@Param("usuario") String usuario);
	
	Persona findByNombre(String nombre);
	
	boolean existsByEmail(String email);
	
}
