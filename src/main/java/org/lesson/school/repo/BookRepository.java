package org.lesson.school.repo;

import org.lesson.school.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer>{

	Object findByTitleContains(String title);

}
