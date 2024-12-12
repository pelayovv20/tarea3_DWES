package com.pelayovv.tarea3DWES.modelo;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "credenciales")
public class Credencial implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NonNull
	private String usuario;
	
	@Column
	@NonNull
	private String password;
	
	@OneToOne
	@JoinColumn(name="idpersona" , unique=true)
	private Persona persona;
	
	
	
	public Credencial() {
		
	}



	public Credencial(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
		 
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	@Override
	public String toString() {
		return "Credencial [id=" + id + ", usuario=" + usuario + ", password=" + password + ", persona=" + persona
				+ "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, password, persona, usuario);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credencial other = (Credencial) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(persona, other.persona) && Objects.equals(usuario, other.usuario);
	}



	



	
	
	
	
	
}
