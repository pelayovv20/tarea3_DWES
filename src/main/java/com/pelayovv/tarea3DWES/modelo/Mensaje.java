package com.pelayovv.tarea3DWES.modelo;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name="mensajes")

public class Mensaje implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private String mensaje;
	
	@Column
	@Temporal(TemporalType.TIME)
	private Date fechahora;
	
	@ManyToOne
	@JoinColumn(name="idejemplar")
	private Ejemplar ejemplar;
	
	
	@ManyToOne
	@JoinColumn(name="idpersona")
	private Persona persona;
	
	public Mensaje() {
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
