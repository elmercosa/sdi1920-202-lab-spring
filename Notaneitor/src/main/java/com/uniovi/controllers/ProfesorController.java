package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Profesor;
import com.uniovi.entities.User;
import com.uniovi.services.ProfesorService;
import com.uniovi.validators.SignUpFormValidator;
import com.uniovi.validators.TeacherFormValidator;

@Controller
public class ProfesorController {
	@Autowired // Inyectar el servicio
	private ProfesorService profesorService;
	
	@Autowired
	private TeacherFormValidator signUpFormValidator;

	@RequestMapping("/profesor/list")
	public String getList(Model model) {
		model.addAttribute("profesorList", profesorService.getProfesores());
		return "profesores/list";
	}

	@RequestMapping(value = "/profesor/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Profesor profesor, BindingResult result) {
		signUpFormValidator.validate(profesor, result);
		if (result.hasErrors()) {
			return "profesores/add";
		}
		profesorService.addProfesor(profesor);
		return "redirect:/profesor/list";
	}
	
	@RequestMapping(value = "/profesor/add")
	public String getMark(Model model) {
		model.addAttribute("profesor", new Profesor());
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

	@RequestMapping(value = "/profesor/edit", method = RequestMethod.POST)
	public String getEdit(Model model, @RequestParam Long id) {
		model.addAttribute("profesor", profesorService.getProfesor(id));
		return "profesores/edit";
	}

	@RequestMapping(value = "/profesor/editar", method = RequestMethod.POST)
	public String setEdit(Model model ,@RequestParam Long id, @ModelAttribute Profesor profesor) {
		profesor.setId(id);
		profesorService.addProfesor(profesor);
		
		return "redirect:/profesor/details/" + profesor.getId();
	}
}
