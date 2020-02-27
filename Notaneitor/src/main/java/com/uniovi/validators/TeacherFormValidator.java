package com.uniovi.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Profesor;
import com.uniovi.entities.User;
import com.uniovi.services.ProfesorService;

@Component
public class TeacherFormValidator implements Validator {
	@Autowired
	private ProfesorService profesorService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Profesor user = (Profesor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (user.getDni().length() < 9) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		if(!Character.isLetter(user.getDni().charAt(user.getDni().length()-1))) {
			errors.rejectValue("dni", "Error.signup.dni.letter");
		}
		Optional<Profesor> aux = profesorService.getProfesores().stream().filter(p -> p.getDni() == user.getDni()).findFirst();
		if(aux.isPresent()) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (user.getNombre().length() < 5 || user.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (user.getApellidos().length() < 5 || user.getApellidos().length() > 24) {
			errors.rejectValue("apellidos", "Error.signup.lastName.length");
		}
		if (user.getCategoria().length() == 0) {
			errors.rejectValue("categoria", "Error.empty");
		}
	}
}