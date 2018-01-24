package com.project.locate.common.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationUtil {

	public static List<String> fromBindingErrors(Errors errors) {
		List<String> validationErrors = new ArrayList<>();
		for (ObjectError objectError : errors.getAllErrors()) {
			validationErrors.add(objectError.getDefaultMessage());
		}
		return validationErrors;
	}
}
