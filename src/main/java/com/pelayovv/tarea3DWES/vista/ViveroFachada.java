package com.pelayovv.tarea3DWES.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pelayovv.tarea3DWES.servicios.Controlador;
import com.pelayovv.tarea3DWES.servicios.ServiciosCredencial;
import com.pelayovv.tarea3DWES.servicios.ServiciosEjemplar;
import com.pelayovv.tarea3DWES.servicios.ServiciosMensaje;
import com.pelayovv.tarea3DWES.servicios.ServiciosPersona;
import com.pelayovv.tarea3DWES.servicios.ServiciosPlanta;

@Component
public class ViveroFachada {
	Scanner sc = new Scanner(System.in);

	@Autowired
	private ServiciosPlanta servPlanta;
	@Autowired
	private ServiciosEjemplar servEjemplar;
	@Autowired
	private ServiciosPersona servPersona;
	@Autowired
	private ServiciosCredencial servCredencial;
	@Autowired
	private ServiciosMensaje servMensaje;
	
	@Autowired
	@Lazy
	private Controlador controlador;
	
	public void menuInicial() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		do {
			System.out.println("Sistema de gestión del vivero");
			System.out.println("1. Acceder como invitado");
			System.out.println("2. Iniciar sesión como personal/administrador");
			System.out.println("3. Salir del vivero");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					//menuInvitados();
					break;
				case 2:
					//autenticarUsuario();
					break;
				case 3:
					System.out.println("Has salido del vivero");

				}
				if (opcion < 1 || opcion > 3) {
					System.out.println("Opción incorrecta");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debes ingresar un número.");
				in.nextLine();
				opcion = 0;
			}
		} while (opcion != 3);

	}
	
	
	
	
	
	
	
	
	
}
