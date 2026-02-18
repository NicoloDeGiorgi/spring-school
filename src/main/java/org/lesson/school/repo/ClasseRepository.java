package org.lesson.school.repo;


import org.lesson.school.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository <Classe, Integer> {

	Object findByNameContains(String name);

}
