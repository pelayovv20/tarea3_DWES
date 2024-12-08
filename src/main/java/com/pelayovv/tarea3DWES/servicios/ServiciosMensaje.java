package com.pelayovv.tarea3DWES.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.modelo.Mensaje;
import com.pelayovv.tarea3DWES.repositorios.MensajeRepository;

@Service
public class ServiciosMensaje {

	@Autowired
	MensajeRepository mensajerepo;
	
	public boolean validarMensaje(String mensaje) {
		if (mensaje == null || mensaje.isEmpty()) {
			return false;
		}
		if (mensaje.length() > 500) {
			return false;
		}
		return true;
	}
	
	public void insertarMensaje(Mensaje m) {
        mensajerepo.saveAndFlush(m);
    }
	
	 public ArrayList<Mensaje> verMensajesPorPlanta(String codigoPlanta) {
	        List<Mensaje> mensajes = mensajerepo.findMensajesByPlanta(codigoPlanta);
	        return new ArrayList<Mensaje>(mensajes);
	    }
	 
	 public ArrayList<Mensaje> verMensajesPorEjemplar(long idEjemplar) {
	        List<Mensaje> mensajes = mensajerepo.findMensajesByEjemplar(idEjemplar);
	        return new ArrayList<Mensaje>(mensajes);
	    }
	 
	 public ArrayList<Mensaje> verMensajesPorPersona(long idPersona) {
	        List<Mensaje> mensajes = mensajerepo.findMensajesByPersona(idPersona);
	        return new ArrayList<Mensaje>(mensajes);
	    }
}
