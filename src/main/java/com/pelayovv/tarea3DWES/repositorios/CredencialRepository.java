package com.pelayovv.tarea3DWES.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial,Long>{

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Credenciales c WHERE c.usuario = :usuario AND c.password = :password")
	boolean existsByUsuarioAndPassword(@Param("usuario") String usuario, @Param("password") String password);
	
	boolean existsByUsuario(String usuario);
}
