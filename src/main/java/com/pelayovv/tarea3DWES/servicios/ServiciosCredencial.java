package com.pelayovv.tarea3DWES.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.repositorios.CredencialRepository;

@Service
public class ServiciosCredencial {
@Autowired
CredencialRepository credencialrepo;
}
