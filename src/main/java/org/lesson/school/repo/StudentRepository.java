package org.lesson.school.repo;

import java.util.List;

import org.lesson.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository <Student, Integer> {

	List<Student> findByNameContains(String name);
	
	
	

}
