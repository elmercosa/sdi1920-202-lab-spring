package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;

@Controller
public class ProfesorController {
	@Autowired // Inyectar el servicio
	private ProfesorService profesorService;

	@RequestMapping("/profesor/list")
	public String getList() {
		return profesorService.getProfesores().toString();
	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Profesor profesor) {
		profesorService.addProfesor(profesor);
		return "Profesor añadido";
	}

	@RequestMapping("/profesor/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return profesorService.getProfesor(id).toString();
	}

	@RequestMapping("/profesor/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		profesorService.deleteProfesor(id);
		return "Profesor eliminado";
	}

	@RequestMapping(value = "/profesor/edit/{id}")
	public String getEdit(@PathVariable Long id) {
		profesorService.getProfesor(id);
		return "Profesor editado";
	}
}
