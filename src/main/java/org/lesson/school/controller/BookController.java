package org.lesson.school.controller;

import java.util.List;

import org.lesson.school.model.Book;
import org.lesson.school.repo.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
	
	
	@Autowired
	private BookRepository bookRepository;

	//INDEX
	@GetMapping
	public String index (Model model) {
		List <Book> books = bookRepository.findAll();		
		model.addAttribute("books", books);		
		
		return "/books/index";		
	}
	
	@GetMapping("/findByTitle/{title}")
	public String findByName(@PathVariable("title") String title, Model model) {
		model.addAttribute("books", bookRepository.findByTitleContains(title));
		return "/books/index";
	}
	
	
}
