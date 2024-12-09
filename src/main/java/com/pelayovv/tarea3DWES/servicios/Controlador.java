package com.pelayovv.tarea3DWES.servicios;

import org.springframework.stereotype.Service;

@Service
public class Controlador {
	
	 private String usuarioActual;
	
	public String getUsuarioActual() {
		return usuarioActual;
	}


	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public void cerrarSesion() {
		this.usuarioActual=null;
	}

}
