package com.pelayovv.tarea3DWES.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;

	@Column(unique = true)
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpersona")
	private List<Mensaje> mensajes = new LinkedList<>();

	public Persona() {

	}

}
