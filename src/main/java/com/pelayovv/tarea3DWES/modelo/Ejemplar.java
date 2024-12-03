package com.pelayovv.tarea3DWES.modelo;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ejemplares")
public class Ejemplar {

	private  static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="idplanta")
	private Planta planta;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idejemplar")
	private List<Mensaje> mensajes = new LinkedList<>();
	
	public Ejemplar() {
		
	}

	
	
	
}
