package com.pelayovv.tarea3DWES.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pelayovv.tarea3DWES.modelo.Planta;
import com.pelayovv.tarea3DWES.repositorios.PlantaRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiciosPlanta {

	@Autowired
	PlantaRepository plantarepo;
	
	
	
	public boolean validarPlanta(Planta p) {
		if ((p == null) || p.getCodigo() == null || p.getCodigo().isEmpty()) {
			return false;
		}

		if (p.getNombreComun() == null || p.getNombreComun().isEmpty()) {
			return false;
		}
		if (p.getNombreCientifico() == null || p.getNombreCientifico().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public boolean validarCodigo(String codigoPlanta) {

		if (codigoPlanta == null || codigoPlanta.isEmpty()) {
			return false;
		} else if (!codigoPlanta.matches("^[a-zA-Z]+$")) {
			return false;
		} 
		if (existeCodigo(codigoPlanta)) {
			return false;
		}

		return true;
	}
	
	public boolean existeCodigo(String codigo) {
		return plantarepo.existsByCodigo(codigo);
	}
	
	public void insertarPlanta(Planta p) {
		plantarepo.save(p);
	}
	
	public List<Planta> verPlantas(){
		return plantarepo.findAll();
	}
	
	public Planta verPorCodigo(String codigo) {
		Optional<Planta> plantas = plantarepo.findByCodigo(codigo);
		return plantas.orElse(null);
	}
	
	@Transactional
	public boolean modificarNombreComun(String codigo, String nombrecomun) {
		Optional<Planta> plantas = plantarepo.findByCodigo(codigo);
		Planta p = new Planta();
		if (!plantas.isPresent()) {
			return false;
		}else {
			p = plantas.get();
			p.setNombreComun(nombrecomun);
			plantarepo.saveAndFlush(p);
			return true;
		}

	}
	@Transactional
	public boolean modificarNombreCientifico(String codigo, String nombrecientifico) {
		Optional<Planta> plantas = plantarepo.findByCodigo(codigo);
		Planta p = new Planta();
		if (!plantas.isPresent()) {
			return false;
		}else {
			p = plantas.get();
			p.setNombreCientifico(nombrecientifico);
			plantarepo.saveAndFlush(p);
			return true;
		}

	}
	
	
	
}
