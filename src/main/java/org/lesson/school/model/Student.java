package org.lesson.school.model;

import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String eta;

	
	@Column (name= "number_of_copies")
	private Integer numberOfCopies;

	@OneToMany(mappedBy = "student", cascade = { CascadeType.REMOVE })
	@JsonManagedReference
	private List<Book> books;

	@Formula("SELECT students.id, students.name, students.eta, COUNT(books.id) " +
	         "FROM students " +
			 "LEFT JOIN books ON students.id = books.student_id "+
			 "GROUP BY students.id, students.name, students.eta; ")

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

}
