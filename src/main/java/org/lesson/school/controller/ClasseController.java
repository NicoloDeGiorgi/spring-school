package org.lesson.school.controller;

import java.util.List;

import org.lesson.school.model.Classe;
import org.lesson.school.repo.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classes")
public class ClasseController {
	
	@Autowired
	private ClasseRepository classeRepository;
	
	//INDEX
		@GetMapping
		public String index (Model model) {
			List <Classe> classes = classeRepository.findAll();		
			model.addAttribute("classes", classes);		
			
			return "/classes/index";		
		}
		
		@GetMapping("/findByName/{name}")
		public String findByName(@PathVariable("name") String name, Model model) {
			model.addAttribute("classes", classeRepository.findByNameContains(name));
			return "/classes/index";
		}

}
