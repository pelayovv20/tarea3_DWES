package com.pelayovv.tarea3DWES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.pelayovv.tarea3DWES.vista.ViveroFachada;


public class Principal implements CommandLineRunner {
	@Autowired
	private ViveroFachada viveroFachada;

	@Override
	public void run(String... args) throws Exception {
		viveroFachada.menuInicial();
		
	}

}
