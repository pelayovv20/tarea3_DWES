package com.pelayovv.tarea3DWES.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Credencial;

import jakarta.transaction.Transactional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial,Long>{
	
	boolean existsByUsuario(String usuario);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Credencial c WHERE c.usuario = :usuario AND c.password = :password")
	boolean existsByUsuarioAndPassword(@Param("usuario") String usuario, @Param("password") String password);
	
	
}
