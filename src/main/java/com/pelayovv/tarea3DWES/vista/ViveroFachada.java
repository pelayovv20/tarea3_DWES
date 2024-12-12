package com.pelayovv.tarea3DWES.vista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pelayovv.tarea3DWES.modelo.Credencial;
import com.pelayovv.tarea3DWES.modelo.Ejemplar;
import com.pelayovv.tarea3DWES.modelo.Mensaje;
import com.pelayovv.tarea3DWES.modelo.Persona;
import com.pelayovv.tarea3DWES.modelo.Planta;
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
					menuInvitados();
					break;
				case 2:
					autenticarUsuario();
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
	
	public void menuInvitados() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("MENÚ DE INVITADOS");
			System.out.println("1. Ver plantas");
			System.out.println("2. Iniciar sesión como personal/administrador");
			System.out.println("3. Volver al menú inicial");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verPlantas();
					break;
				case 2:
					autenticarUsuario();
					break;
				case 3:
					System.out.println("Volviendo al menú inicial");
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

	public void autenticarUsuario() {
		Scanner in = new Scanner(System.in);
		boolean autenticarUsuario = false;
		try {
			System.out.println("Usuario");
			String usuarioActual = in.nextLine();
			System.out.println("Contraseña");
			String contraseña = in.nextLine();

			autenticarUsuario = servCredencial.autenticarUsuario(usuarioActual, contraseña);

			if (autenticarUsuario) {

				controlador.setUsuarioActual(usuarioActual);
				if (usuarioActual.equalsIgnoreCase("admin") && contraseña.equalsIgnoreCase("admin")) {

					System.out.println("Bienvenido " + controlador.getUsuarioActual());
					menuAdministrador();
				} else {
					System.out.println("Bienvenido " + controlador.getUsuarioActual());
					menuPersonal();
				}
			} else {
				System.out.println("No existe ninguna persona con usuario " + usuarioActual.toUpperCase()
						+ " y contraseña " + contraseña.toUpperCase());
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void menuAdministrador() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("MENÚ DE ADMINISTRADOR");
			System.out.println("1. Gestionar Plantas");
			System.out.println("2. Gestionar Ejemplares.");
			System.out.println("3. Gestionar Personas.");
			System.out.println("4. Gestionar Mensaje.");
			System.out.println("5. Cerrar Sesión");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					menuPrincipalPlantas();
					break;
				case 2:
					menuPrincipalEjemplares();
					break;
				case 3:
					menuPrincipalPersonas();
					break;
				case 4:
					menuPrincipalMensajes();
					break;
				case 5:
					System.out.println("Cerrando sesión como " + controlador.getUsuarioActual());
					controlador.cerrarSesion();

				}

				if (opcion < 1 || opcion > 5) {
					System.out.println("Opción incorrecta");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debes ingresar un número.");
				in.nextLine();
				opcion = 0;
			}
		} while (opcion != 5);

	}

	public void menuPersonal() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("MENÚ DEL PERSONAL");
			System.out.println("1. Ver plantas");
			System.out.println("2. Gestionar ejemplares");
			System.out.println("3. Gestionar mensajes");
			System.out.println("4. Cerrar Sesión");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verPlantas();

					break;
				case 2:
					menuPrincipalEjemplares();
					break;
				case 3:
					menuPrincipalMensajes();
					break;
				case 4:
					System.out.println("cerrando sesión como " + controlador.getUsuarioActual());
					controlador.cerrarSesion();
				}
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debes ingresar un número.");
				in.nextLine();
				opcion = 0;
			}

		} while (opcion != 4);
	}

	public void menuPrincipalPlantas() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("Seleccione una opcion:");
			System.out.println("1.  Ver plantas.");
			System.out.println("2.  Crear nueva planta.");
			System.out.println("3.  Modificar datos de Planta(nombre común/nombre científico).");
			System.out.println("4.  Volver al menu Principal");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verPlantas();

					break;
				case 2:
					insertarPlanta();

					break;
				case 3:
					menuModificarPlantas();
					break;
				case 4:
					System.out.println("Saliendo del menú de Plantas");

				}
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debes ingresar un número.");
				in.nextLine();
				opcion = 0;
			}
		} while (opcion != 4);

	}

	public void menuModificarPlantas() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {
			System.out.println("Selecciona una opcion:");
			System.out.println("1.  Modificar nombre común de una planta.");
			System.out.println("2.  Modificar nombre científico de una planta.");
			System.out.println("3.  Volver al menú principal de plantas.");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					modificarNombreComunPlanta();
					break;
				case 2:
					modificarNombreCientificoPlanta();
					break;
				case 3:
					System.out.println("Volviendo al menú principal de plantas");

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

	public void menuPrincipalEjemplares() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;

		do {

			System.out.println("Seleccione una opcion:");
			System.out.println("1.  Ver ejemplares por tipo de planta.");
			System.out.println("2.  Crear nuevo ejemplar.");
			System.out.println("3.  Volver al menu Principal");
			try {

				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verEjemplares();
					break;
				case 2:
					insertarEjemplarMensaje();
					break;
				case 3:
					System.out.println("Volviendo al menú principal");

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

	public void menuPrincipalPersonas() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		do {

			System.out.println("Seleccione una opcion:");
			System.out.println("1.  Ver personas.");
			System.out.println("2.  Registrar nueva persona.");
			System.out.println("3.  Volver al menu Principal");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verPersonas();

					break;
				case 2:
					insertarPersonaCredencial();

					break;
				case 3:
					System.out.println("Saliendo del menú de Personas");

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

	public void menuPrincipalMensajes() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		do {
			System.out.println("Seleccione una opcion:");
			System.out.println("1.  Ver mensajes.");
			System.out.println("2.  Crear nuevo mensaje.");
			System.out.println("3.  Volver al menu Principal");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					menuVerMensajes();
					break;
				case 2:
					//insertarMensaje();
					break;
				case 3:
					System.out.println("Saliendo del menú de Mensajes");

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

	public void menuVerMensajes() {
		Scanner in = new Scanner(System.in);
		int opcion = 0;
		do {
			System.out.println("1.  Ver mensajes por ejemplar.");
			System.out.println("2.  Ver mensajes por persona.");
			System.out.println("3.  Ver mensajes por tipo de planta.");
			System.out.println("4.  Ver mensajes por rango de fechas.");
			System.out.println("5.  Volver al menú de mensajes.");
			try {
				opcion = in.nextInt();

				switch (opcion) {
				case 1:
					verMensajesEjemplar();
					break;
				case 2:
					verMensajesPersona();
					break;
				case 3:
					verMensajesPlanta();

					break;
				case 4:
					verMensajesFecha();
					break;
				case 5:
					System.out.println("Saliendo del menú de Mensajes");

				}
				if (opcion < 1 || opcion > 5) {
					System.out.println("Opción incorrecta");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debes ingresar un número.");
				in.nextLine();
				opcion = 0;
			}

		} while (opcion != 5);
	}

	/**
	 * Método para insertar una planta
	 * 
	 */
	public Planta insertarPlanta() {
		Scanner in = new Scanner(System.in);
		
		verPlantas();
		Planta p;
		boolean correcto = false;
		boolean validarCodigo = false;
		do {
			p = new Planta();
			do {
				System.out.println("Codigo de la planta");
				String codigo = in.nextLine().trim().toUpperCase();

				validarCodigo = servPlanta.validarCodigo(codigo);

				if (!validarCodigo) {
					System.out.println("Codigo de planta incorrecto");
				} else {
					p.setCodigo(codigo);
				}
			} while (!validarCodigo);

			System.out.print("Nombre común: ");
			String nombrecomun = in.nextLine();
			p.setNombreComun(nombrecomun);
			System.out.print("Nombre científico: ");
			String nombrecientifico = in.nextLine();
			p.setNombreCientifico(nombrecientifico);
			correcto = servPlanta.validarPlanta(p);
			if (!correcto) {
				System.out.println("Los datos que has introducido no son correctos.");
			}

		} while (!correcto);

		try {
			servPlanta.insertarPlanta(p);
			System.out.println("Planta insertada");
		} catch (Exception ex) {
			System.out.println("Error al insertar la planta: " + ex.getMessage());
		}
		return p;
	}

	/**
	 * Método para insertar un ejemplar con su mensaje correspondiente
	 * 
	 */
	public Ejemplar insertarEjemplarMensaje() {
		Scanner in = new Scanner(System.in);
		
		Ejemplar e = null;
		Mensaje m;
		Planta p;
		Persona pers;
		boolean correcto = false;
		boolean validarPlanta = false;
		do {
			
			System.out.println("Codigo de planta");
			String codigoPlanta = in.nextLine().trim().toUpperCase();
			validarPlanta = servPlanta.existeCodigo(codigoPlanta);
			if (validarPlanta == true) {
				e.getPlanta().setCodigo(codigoPlanta);
				e.setNombre(codigoPlanta);
				correcto = true;

			} else if (validarPlanta == false) {
				System.out.println("Codigo de planta incorrecto");
				continue;
			}
		
		p = servPlanta.verPorCodigo(codigoPlanta);
		e = new Ejemplar();
		 
		
			e.setPlanta(p);
			e.setNombre(e.getPlanta().getCodigo() + "_" + e.getId());
			servEjemplar.insertarEjemplar(e);
			servEjemplar.cambiarNombreEjemplar(e.getId(), e.getNombre());
			String mensaje = "Añadido el ejemplar " + e.getNombre();
			LocalDateTime fechaHora = LocalDateTime.now();
			String usuarioActual = controlador.getUsuarioActual();
			pers = servPersona.verPorNombre(usuarioActual);
			m = new Mensaje(fechaHora, mensaje, e, pers);
			servMensaje.insertarMensaje(m);
				System.out.println("Mensaje añadido correctamente.");
			
			
	} while (!correcto);
		return e;
	}

	/**
	 * Método para insertar una persona con sus credenciales correspondientes
	 * 
	 */
	public Persona insertarPersonaCredencial() {
		Scanner in = new Scanner(System.in);
		Persona p;
		Credencial c;
		boolean validarPersona = false;
		boolean validarCredencial = false;
		boolean validarUsuario = false;
		String usuario = "";
		String contraseña = "";

		p = new Persona();

		do {
			System.out.println("Nombre");
			String nombre = in.nextLine().trim();
			p.setNombre(nombre);
			System.out.println("Email");
			String email = in.nextLine();
			p.setEmail(email);
			validarPersona = servPersona.validarPersona(p);
			if (validarPersona == false) {
				System.out.println("Nombre o email incorrectos");
			}

		} while (!validarPersona);
		 servPersona.insertarPersona(p);

		c = new Credencial();
		do {
			do {
			System.out.println("Usuario");
			usuario = in.nextLine().trim();
			validarUsuario = servCredencial.validarUsuario(usuario);
			if (validarUsuario == true) {
				c.setUsuario(usuario);
			}else {
				System.out.println("Nombre de usuario incorrecto");
			}
			
			}while(!validarUsuario);
			boolean validarContraseña = false;
			do {
				System.out.println("Contraseña");
				contraseña = in.nextLine().trim();
				validarContraseña = servCredencial.validarContraseña(contraseña);
				if (validarContraseña == false) {
					System.out.println(
							"La contraseña debe tener como mínimo 8 caractéres y que no tenga espacios en blanco");
				}
			} while (!validarContraseña);
			c.setPassword(contraseña);
			validarCredencial = servCredencial.validarCredencial(c);
			if (validarCredencial == false) {
				System.out.println("Usuario o contraseña incorrecto");
			}
		} while (!validarCredencial);

		
			c.setPersona(p);
			p.setCredenciales(c);
			
		servPersona.insertarPersona(p);
		System.out.println("Persona creada");

		return p;
	}

	/**
	 * Método para insertar mensajes
	 */
//	public void insertarMensaje() {
//		Scanner in = new Scanner(System.in);
//		Mensaje m = null;
//		long idEjemplar = 0;
//		boolean validarEjemplar = false;
//		boolean validarMensaje = false;
//
//		try {
//
//			System.out.println("Id del ejemplar ");
//			idEjemplar = in.nextLong();
//			in.nextLine();
//			if (idEjemplar < 7) {
//				System.out.println("Id de ejemplar no encontrado");
//			} else {
//				String mensaje = "";
//
//				do {
//					System.out.println("Introduce el mensaje: ");
//					mensaje = in.nextLine();
//					validarMensaje = servMensaje.validarMensaje(mensaje);
//					if (validarMensaje == false) {
//						System.out.println("Mensaje incorrecto");
//					} else {
//						validarMensaje = true;
//						String usuarioActual = controlador.getUsuarioActual();
//						long idUsuario = controlador.getServiciosPersona().personaAutenticada(usuarioActual);
//						m = new Mensaje(LocalDateTime.now(), mensaje, idEjemplar, idUsuario);
//						if (controlador.getServiciosMensaje().insertar(m) > 0) {
//							System.out.println("Mensaje añadido");
//							validarMensaje = true;
//						} else {
//							System.out.println("Mensaje no añadido");
//						}
//					}
//				} while (!validarMensaje);
//			}
//
//		} catch (InputMismatchException e) {
//			System.out.println("Debes introducir un número válido.");
//			in.nextLine();
//		}
//
//	}

	/**
	 * Método para ver las plantas que hay en la base de datos
	 */
	public void verPlantas() {
		Scanner in = new Scanner(System.in);
		
			ArrayList<Planta> plantas = (ArrayList<Planta>) servPlanta.verPlantas();

			if (plantas == null || plantas.isEmpty()) {
				System.out.println("No se ha encontrado ninguna planta");
				return;
			} else {
				System.out.println("LISTADO DE PLANTAS DEL VIVERO");
				for (Planta p : plantas) {
					System.out.println(p);
				}
			}
		
	}

	/**
	 * Método para ver los ejemplares que hay en la base de datos
	 */
	public void verEjemplares() {
		Scanner in = new Scanner(System.in);
		verPlantas();
		boolean validarCodigoPlanta = false;
		try {
			do {
				System.out.println("Codigo de la planta");
				String codigoPlanta = in.nextLine().trim().toUpperCase();
				validarCodigoPlanta = servPlanta.existeCodigo(codigoPlanta);
				if (validarCodigoPlanta == false) {
					System.out.println("Codigo de planta no encontrado");
				} else {
					List<Ejemplar> ejemplares = servEjemplar.verEjemplares();
					if (ejemplares.isEmpty()) {
						System.out.println("No se ha encontrado ningún ejemplar");
					} else {
						System.out.println("Ejemplares con el código " + codigoPlanta + ":");
						for (Ejemplar e : ejemplares) {
							System.out.println("ID EJEMPLAR: " + e.getId() + " - " + " NOMBRE: " + e.getNombre());

						}
					}
				}

			} while (!validarCodigoPlanta);
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método para ver las personas registradas en la base de datos
	 */
	public void verPersonas() {
		Scanner in = new Scanner(System.in);
		try {
			ArrayList<Persona> personas = (ArrayList<Persona>) servPersona.verPersonas();

			if (personas == null || personas.isEmpty()) {
				System.out.println("No se ha encontrado ninguna persona");
				return;
			} else {
				System.out.println("LISTADO DE PERSONAS DEL VIVERO");
				for (Persona p : personas) {
					System.out.println(p);
				}
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Método para ver los mensajes dependiendo del ejemplar introducido
	 */
	public void verMensajesEjemplar() {
		Scanner in = new Scanner(System.in);
		long idEjemplar = 0;

		try {
			do {
				System.out.print("Id del ejemplar: ");
				idEjemplar = in.nextLong();

				if (idEjemplar < 7) {
					System.out.println("ID de ejemplar no encontrado");
				}
			} while (idEjemplar < 7);
		} catch (Exception e) {
			System.out.println("Error: debe ingresar un número válido.");
			in.next();
		}

		ArrayList<Mensaje> mensajes = servMensaje.verMensajesEjemplar(idEjemplar);
		if (mensajes.isEmpty()) {
			System.out.println("No se ha encontrado ningún mensaje");
		} else {
			System.out.println("Ejemplares con el código " + idEjemplar + ":");
			for (Mensaje m : mensajes) {
				System.out.println(m);
			}
		}

	}

	/**
	 * Método para ver los mensajes dependiendo de la persona introducido
	 */
	public void verMensajesPersona() {
		Scanner in = new Scanner(System.in);
		long idPersona = -1;

		try {
			do {
				System.out.print("Id de la persona: ");
				idPersona = in.nextLong();

				if (idPersona != 0 && idPersona < 5) {
					System.out.println("ID de persona no encontrado");
				} else {
					ArrayList<Mensaje> mensajes = servMensaje.verMensajesPersona(idPersona);
					if (mensajes.isEmpty()) {
						System.out.println("No se ha encontrado ningún mensaje");
					} else {
						System.out.println("Mensajes de la persona " + idPersona + ":");
						for (Mensaje m : mensajes) {
							System.out.println(m);
						}
					}
				}
			} while (idPersona != 0 && idPersona < 5);
		} catch (Exception e) {
			System.out.println("Error: debe ingresar un número válido.");
			in.next();
		}

	}

	/**
	 * Método para ver los mensajes dependiendo de la planta introducida
	 */
	public void verMensajesPlanta() {
		Scanner in = new Scanner(System.in);
		boolean validarCodigoPlanta = false;

		try {
			do {
				System.out.print("Codigo de planta: ");
				String codigoPlanta = in.nextLine();
				validarCodigoPlanta = servPlanta.existeCodigo(codigoPlanta);
				if (validarCodigoPlanta == false) {
					System.out.println("Codigo de planta no encontrado");
				} else {

					ArrayList<Mensaje> mensajes = servMensaje.verMensajesPlanta(codigoPlanta);
					if (mensajes.isEmpty()) {
						System.out.println("No se ha encontrado ningún mensaje");
					} else {
						System.out.println("Mensajes de la planta" + codigoPlanta.toUpperCase() + ":");
						for (Mensaje m : mensajes) {
							System.out.println(m);
						}
					}

				}
			} while (!validarCodigoPlanta);

		} catch (Exception e) {
			System.out.println("Error: debe ingresar un número válido.");
			in.next();
		}
	}

	public void verMensajesFecha() {
		System.out.println("No realizado");
	}

	/**
	 * Método para modificar el nombre común de una planta
	 */
	public void modificarNombreComunPlanta() {
		Scanner in = new Scanner(System.in);
		boolean validarPlanta;
		boolean modificarNombreComun;
		String codigoPlanta;
		try {
			do {
				System.out.println("Codigo de la planta");
				codigoPlanta = in.nextLine().trim().toUpperCase();
				validarPlanta = servPlanta.existeCodigo(codigoPlanta);
				if (validarPlanta == false) {
					System.out.println("Codigo de planta incorrecto");
				}
			} while (!validarPlanta);

			System.out.println("Actualiza el nombre común de la planta");
			String nombrecomun = in.nextLine().trim();

			modificarNombreComun = servPlanta.modificarNombreComun(codigoPlanta, nombrecomun);
			if (modificarNombreComun == true) {
				System.out.println("Nombre común actualizado");
			} else {
				System.out.println("Error al  actualizar el nombre común");
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

	/**
	 * Método para modificar el nombre científico de una planta
	 */
	public void modificarNombreCientificoPlanta() {
		Scanner in = new Scanner(System.in);
		boolean validarPlanta;
		boolean modificarNombreCientifico;
		String codigoPlanta;
		try {
			do {
				System.out.println("Codigo de la planta");
				codigoPlanta = in.nextLine().trim().toUpperCase();
				validarPlanta = servPlanta.existeCodigo(codigoPlanta);
				if (validarPlanta == false) {
					System.out.println("Codigo de planta incorrecto");
				}
			} while (!validarPlanta);
			

			System.out.println("Actualiza el nombre científico de la planta");
			String nombrecientifico = in.nextLine().trim();

			modificarNombreCientifico = servPlanta.modificarNombreCientifico(codigoPlanta,
					nombrecientifico);
			if (modificarNombreCientifico == true) {
				System.out.println("Nombre cientifico actualizado");
			} else {
				System.out.println("Error al actualizar el nombre científico");
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}
	
	
	
	
	
	
	
}
