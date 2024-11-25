package com.pelayovv.tarea3DWES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.pelayovv.tarea3DWES.modelo.Planta;
import com.pelayovv.tarea3DWES.servicios.ServiciosPlanta;

public class Principal implements CommandLineRunner {
	
	@Autowired
	ServiciosPlanta servPlanta;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");
		
		
		Planta p = new Planta();
		servPlanta.validarPlanta(p);
		System.out.println("-------------------");
		
		
		System.out.println("FIN");
		
	}

}
