package org.lesson.school.controller;

import java.util.List;


import org.lesson.school.model.Student;
import org.lesson.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	//INDEX
	@GetMapping
	public String index (Authentication authentication, Model model) {
		List <Student> students = service.findAllSortedByUpdate();		
		model.addAttribute("students", students);
		model.addAttribute("username", authentication.getName()); 
		
		return "/students/index";		
	}
	
	@GetMapping("/findByName/{name}")
	public String findByName(@PathVariable("name") String name, Model model) {
		model.addAttribute("students", service.findByAllByName(name));
		return "/students/index";
	}
	
	//SHOW
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("student", service.getById(id));
		return "/students/show";

	}
	
	
	// CREATE
		@GetMapping("/create")
		public String create(Model model) {
			model.addAttribute("student", new Student());
			return "/students/create";
		}
		
		// STORE - CREATE
		@PostMapping("/create")
		public String store(@Valid @ModelAttribute("student") Student formStudent,
				BindingResult bindingResult,
				Model model) {
			if (bindingResult.hasErrors()) {
				return "/students/create";
			}
			service.createStudent(formStudent);
			return "redirect:/students";
		}
	
		// EDIT
		@GetMapping("/edit/{id}") // cerchiamo lo studente e lo inserisco nel modello
		public String edit(@PathVariable("id")
		Integer id, Model model) {
			model.addAttribute("student", service.getById(id));

			return "students/edit";
		}
		
		// UPDATE
		@PostMapping("/edit/{id}")
		public String update(
				// Validazione
				@Valid @ModelAttribute("student") Student formUpdateStudent,
				BindingResult bindingResult,
				Model model) {
			if (bindingResult.hasErrors()) {
				return "/students/edit";
			} // se ci sono errori "torna indetro" altrimenti salva
			service.updateStudent(formUpdateStudent);

			return "redirect:/students"; // ritorna alla index
		}
		
		// DELETE
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {
			service.deleteStudentById(id); // prendo la repo, trovo il tickets attraverso id e cancello dal database

			return "redirect:/students"; // ritorna alla index
		}


		
}
