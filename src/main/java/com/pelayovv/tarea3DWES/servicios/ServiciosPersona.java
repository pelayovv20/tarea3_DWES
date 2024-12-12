package com.pelayovv.tarea3DWES.servicios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.modelo.Persona;
import com.pelayovv.tarea3DWES.repositorios.PersonaRepository;

@Service
public class ServiciosPersona {
	@Autowired
	PersonaRepository personarepo;

	public boolean validarPersona(Persona p) {
		if ((p == null) || p.getNombre() == null || p.getNombre().isEmpty()) {
			return false;
		}
		if (p.getNombre().length() < 3 || p.getNombre().length() > 20) {
			return false;
		}
		if (p.getEmail() == null || p.getEmail().isEmpty()) {
			return false;
		}
		if (p.getEmail().length() < 5 || !p.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
				|| p.getEmail().length() > 40) {
			return false;
		}
		
		return true;
	}
	
	public boolean existeEmail(String email) {
        return personarepo.existsByEmail(email);
    }
	
	public long personaAutenticada(String usuario) {
        Long idPersona = personarepo.personaAutenticada(usuario);
        return (idPersona != null) ? idPersona : -1;
    }
	
	public void insertarPersona(Persona p) {
        personarepo.saveAndFlush(p);
    }

    public Collection<Persona> verPersonas() {
        return personarepo.findAll();
    }
    
    public Persona verPorNombre(String nombre){
    	return personarepo.findByNombreContainingIgnoreCase(nombre);
    }
	
}
