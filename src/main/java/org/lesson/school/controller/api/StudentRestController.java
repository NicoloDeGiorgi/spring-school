package org.lesson.school.controller.api;

import java.util.List;

import org.lesson.school.model.Student;
import org.lesson.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/students")
public class StudentRestController {
	
	@Autowired
	private StudentService service;
	
	
	@GetMapping
	public List<Student> index(@RequestParam(required = false) String word) {

	    if (word != null && !word.isEmpty()) {
	        return service.findByAllByName(word);
	    }

	    return service.findAllSortedByUpdate();
	}
}

