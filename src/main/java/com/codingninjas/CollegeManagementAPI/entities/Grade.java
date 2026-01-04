package com.codingninjas.CollegeManagementAPI.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private double marks;

	public Grade(double marks) {
		super();
		this.marks = marks;
	}

	public Grade() {
	}

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "course_id")
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double value) {
		this.marks = value;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
