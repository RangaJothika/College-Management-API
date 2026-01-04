package com.codingninjas.CollegeManagementAPI.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.JoinColumn;
import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	public Student(String name) {
		super();
		this.name = name;
	}

	public Student() {
	}

//	@JsonIgnoreProperties({"students","grades"})
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="student_course",joinColumns=@JoinColumn(name="student_id"),inverseJoinColumns=@JoinColumn(name="course_id"))
//	Using HashSet to avoid duplicate entries.
	private Set<Course> courses = new HashSet<>();
	
	@OneToMany(mappedBy = "student",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Grade> gradeList = new ArrayList<>();

	public Student(List<Grade> gradeList) {
		super();
		this.gradeList = gradeList;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
