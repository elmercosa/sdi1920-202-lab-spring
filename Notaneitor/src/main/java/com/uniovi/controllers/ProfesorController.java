package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Profesor;
import com.uniovi.services.ProfesorService;

@RestController
public class ProfesorController {
	@Autowired // Inyectar el servicio
	private ProfesorService profesorService;

	@RequestMapping("/profesor/list")
	public String getList() {
		return profesorService.getProfesores().toString();

	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setMark(@RequestParam String dni, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String categoria ) {
		profesorService.addProfesor(new Profesor(dni, nombre, apellido, categoria));
		return "Profesor añadido";
	}

	@RequestMapping("/profesor/details/{dni}")
	public String getDetail(@PathVariable String dni) {
		return profesorService.getProfesor(dni).toString();
	}

	@RequestMapping("/profesor/delete/{dni}")
	public String deleteMark(@PathVariable String dni) {
		profesorService.deleteProfesor(dni);
		return "Profesor eliminado";
	}

	@RequestMapping(value = "/profesor/edit/{dni}")
	public String getEdit(@PathVariable String dni) {
		profesorService.getProfesor(dni);
		return "Profesor editado";
	}

//	@RequestMapping(value = "/profesor/edit/{id}", method = RequestMethod.POST)
//	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Mark mark) {
//		mark.setId(id);
//		marksService.addMark(mark);
//		return "redirect:/mark/details/" + id;
//	}

}
