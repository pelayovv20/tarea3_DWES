package com.pelayovv.tarea3DWES.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")
public class Planta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(unique=true)
	@NonNull
	private String codigo;
	
	
	@Column
	@NonNull
	private String nombrecomun;
	
	@Column
	private String nombrecientifico;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idplanta")
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	
	public  Planta() {
		
	}

	
	
	public Planta(String codigo, String nombrecomun, String nombrecientifico, List<Ejemplar> ejemplares) {
		super();
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
		this.ejemplares = ejemplares;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getNombreComun() {
		return nombrecomun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombrecomun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombrecientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombrecientifico = nombreCientifico;
	}
	

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}


	

	@Override
	public String toString() {
		String ret = "";
		ret += "Codigo de planta: " + this.codigo;
		ret += "\nNombre científico: " + this.nombrecientifico;
		ret += "\nNombre común: " + this.nombrecomun;
		ret += "\n----------------------------------";
		return ret;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigo, ejemplares, id, nombrecientifico, nombrecomun);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(ejemplares, other.ejemplares)
				&& Objects.equals(id, other.id) && Objects.equals(nombrecientifico, other.nombrecientifico)
				&& Objects.equals(nombrecomun, other.nombrecomun);
	}
	

	

	
	
	
	
	
	
}
