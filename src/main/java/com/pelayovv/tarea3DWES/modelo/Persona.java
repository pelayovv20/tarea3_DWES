package com.pelayovv.tarea3DWES.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NonNull
	private String nombre;

	@Column(unique = true)
	@NonNull
	private String email;
	
	@OneToOne(mappedBy = "persona", cascade= CascadeType.ALL)
	private Credencial credenciales;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Mensaje> mensajes = new LinkedList<Mensaje>();

	public Persona() {

	}

	public Persona(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credencial getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(Credencial credenciales) {
		this.credenciales = credenciales;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	@Override
	public String toString() {
		String ret = "";
		ret += "id: " + this.id;
		ret += "\nNombre: " + this.nombre;
		ret += "\nEmail: " + this.email;
		ret += "\nUsuario: " + this.credenciales.getUsuario();
		ret += "\nContraseña: " + this.credenciales.getPassword();
		ret += "\n------------------------------------";
		return ret;
	}

	@Override
	public int hashCode() {
		return Objects.hash(credenciales, email, id, mensajes, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(credenciales, other.credenciales) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(mensajes, other.mensajes)
				&& Objects.equals(nombre, other.nombre);
	}

	

	
	
	

}
