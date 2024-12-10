package com.pelayovv.tarea3DWES.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.modelo.Credencial;
import com.pelayovv.tarea3DWES.modelo.Persona;
import com.pelayovv.tarea3DWES.repositorios.CredencialRepository;
import com.pelayovv.tarea3DWES.repositorios.PersonaRepository;

@Service
public class ServiciosCredencial {
@Autowired
CredencialRepository credencialrepo;
@Autowired
PersonaRepository personarepo;

public boolean validarCredencial(Credencial c) {
	if (c == null) {
		return false;
	}
	if (c.getUsuario() == null || c.getUsuario().isEmpty()) {
		return false;
	
	} else if (c.getPassword() == null || c.getPassword().isEmpty()) {
		return false;
	}
	return true;
}

public boolean autenticarUsuario(String usuario, String password) {
    return credencialrepo.usuarioExistente(usuario, password);
    }

public boolean existeUsuario(String usuario) {
    return credencialrepo.existsByUsuario(usuario);
}

public void insertarCredencial(String usuario, String password, Long idpersona) {
    Persona p = personarepo.findById(idpersona).orElse(null);
    Credencial c = new Credencial();
    c.setUsuario(usuario);
    c.setPassword(password);
    c.setPersona(p);
    credencialrepo.saveAndFlush(c);
}


}
