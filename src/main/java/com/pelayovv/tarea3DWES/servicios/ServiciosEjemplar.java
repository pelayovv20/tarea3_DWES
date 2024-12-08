package com.pelayovv.tarea3DWES.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.modelo.Ejemplar;
import com.pelayovv.tarea3DWES.repositorios.EjemplarRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiciosEjemplar {

	@Autowired
	EjemplarRepository ejemplarrepo;
	
	public void insertarEjemplar(Ejemplar e) {
		ejemplarrepo.saveAndFlush(e);
	}
	
	public List<Ejemplar> verEjemplares(){
		return ejemplarrepo.findAll();
	}
	
	public Ejemplar verPorId(long idEjemplar) {
        Optional<Ejemplar> ejemplares = ejemplarrepo.findById(idEjemplar);
        return ejemplares.orElse(null);
    }
	
	 @Transactional
	    public boolean cambiarNombreEjemplar(long idejemplar, String nombre) {
	        int nombrenuevo = ejemplarrepo.cambiarNombreEjemplar(idejemplar, nombre);
	        return nombrenuevo > 0;
	    }
	 
	 public ArrayList<Ejemplar> ejemplaresPorTipoPlanta(String codigo) {
	        List<Ejemplar> ejemplares = ejemplarrepo.ejemplaresPorTipoPlanta(codigo);
	        return new ArrayList<Ejemplar>(ejemplares);
	    }

}
