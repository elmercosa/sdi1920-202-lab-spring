package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Profesor;

@Service
public class ProfesorService {
	List<Profesor> profesores = new ArrayList<Profesor>();

	@PostConstruct
	public void init() {
		profesores.add(new Profesor("111111", "Profesor 1", "Profesor 1", "Categoria 1"));
		profesores.add(new Profesor("222222", "Profesor 2", "Profesor 2", "Categoria 2"));
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public Profesor getProfesor(String dni) {
		return profesores.stream().filter(profesor -> profesor.getDni().equals(dni)).findFirst().get();
	}

	public void addProfesor(Profesor profesor) {
		profesores.add(profesor);
	}

	public void deleteProfesor(String dni) {
		profesores.removeIf(profesor -> profesor.getDni().equals(dni));
	}

}
