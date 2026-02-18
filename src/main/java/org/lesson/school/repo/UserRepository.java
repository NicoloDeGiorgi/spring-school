package org.lesson.school.repo;

import java.util.Optional;

import org.lesson.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository <User, Integer>{
	
	public Optional<User> findByUsername(String username);
	
}
