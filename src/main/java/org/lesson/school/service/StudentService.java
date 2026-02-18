package org.lesson.school.service;

import java.util.List;

import org.lesson.school.model.Student;
import org.lesson.school.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	
	@Autowired
	private StudentRepository studentRepository;
	
	//INDEX
	public List <Student> findAllSortedByUpdate(){
		return studentRepository.findAll(Sort.by("name"));
	}
	
	public List <Student> findByAllByName(String searchedText){
		return studentRepository.findByNameContains(searchedText);
	}
	
	
	//SHOW
	public Student getById (Integer id) {
		return studentRepository.findById(id).get();
		}
	
	
	//CREATE
	public Student createStudent(Student student) {
	    return studentRepository.save(student);
	}


	//UPDATE
	public Student updateStudent (Student student) {
	   return studentRepository.save(student);
	}

	//Delete
	public void deleteStudentById(Integer id) {
	   studentRepository.deleteById(id);
	}

	
}
