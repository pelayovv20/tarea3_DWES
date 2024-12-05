package com.pelayovv.tarea3DWES.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Ejemplar;

@Repository
public interface EjemplarRepository extends JpaRepository <Ejemplar, Long> {

}
