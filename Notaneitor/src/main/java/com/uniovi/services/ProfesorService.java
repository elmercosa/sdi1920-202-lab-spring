package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Profesor;
import com.uniovi.repositories.ProfesorRepository;

@Service
public class ProfesorService {
	@Autowired
	private ProfesorRepository profesorRepository;

	public List<Profesor> getProfesores() {
		List<Profesor> profesores = new ArrayList<Profesor>();
		profesorRepository.findAll().forEach(profesores::add);
		return profesores;
	}

	public Profesor getProfesor(Long id	) {
		return profesorRepository.findById(id).get();
	}
	

	public void addProfesor(Profesor profesor) {
		profesorRepository.save(profesor);
	}

	public void deleteProfesor(Long id) {
		profesorRepository.deleteById(id);
	}

}
