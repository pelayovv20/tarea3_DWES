package com.pelayovv.tarea3DWES.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pelayovv.tarea3DWES.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {

	
    

    @Query("SELECT m FROM Mensaje m INNER JOIN m.ejemplar e INNER JOIN e.planta p WHERE p.codigo = :codigoPlanta")
    List<Mensaje>findMensajesByPlanta(@Param("codigoPlanta") String codigoPlanta);

    @Query("SELECT m FROM Mensaje m WHERE m.ejemplar.id = :idEjemplar")
    List<Mensaje> findMensajesByEjemplar(@Param("idEjemplar")long idEjemplar);
    
    @Query("SELECT m FROM Mensaje m WHERE m.persona.id = :idPersona")
    List<Mensaje>findMensajesByPersona(@Param("idPersona")long idPersona);
}
