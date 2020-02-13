package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String getList(Model model) {
		model.addAttribute("profesorList", profesorService.getProfesores());
		return "profesores/list";
	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Profesor profesor) {
		profesorService.addProfesor(profesor);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping(value = "/profesor/add")
	public String getMark() {
		return "profesores/add";
	}


	@RequestMapping("/profesor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("profesor", profesorService.getProfesor(id));
		return "profesores/details";
	}

	@RequestMapping("/profesor/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		profesorService.deleteProfesor(id);
		return "redirect:/profesor/list";
	}

	@RequestMapping(value = "/profesor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("profesor", profesorService.getProfesor(id));
		return "profesores/edit";
	}

	@RequestMapping(value = "/profesor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Profesor profesor) {
		profesor.setId(id);
		profesorService.addProfesor(profesor);
		return "redirect:/profesor/details/" + id;
	}
}
