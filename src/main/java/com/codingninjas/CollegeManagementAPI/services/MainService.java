package com.codingninjas.CollegeManagementAPI.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingninjas.CollegeManagementAPI.entities.Course;
import com.codingninjas.CollegeManagementAPI.entities.CourseMarks;
import com.codingninjas.CollegeManagementAPI.entities.Grade;
import com.codingninjas.CollegeManagementAPI.entities.Student;
import com.codingninjas.CollegeManagementAPI.repository.CourseRepository;
import com.codingninjas.CollegeManagementAPI.repository.GradeRepository;
import com.codingninjas.CollegeManagementAPI.repository.StudentRepository;

@Service
public class MainService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	GradeRepository gradeRepository;

	/*
	 1. Write a method with name "getCoursesNameByStudentId" which takes "int id" as parameter and
	    returns the list of string containing the name of all the courses enrolled by the given student id.

		public List<String> getCoursesNameByStudentId(int id) {

			}
	 */
	public double getAverageGradesOfStudent(int id) {
		return gradeRepository.getAverageGradesOfStudent(id);
	}

	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public void setStudent(Student student) {
		studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Course getCourseById(int id) {
		return courseRepository.findById(id).get();
	}

	public void setCourses(List<CourseMarks> courses, int id) {
		Student student = this.getStudentById(id);

		for(CourseMarks courseMarks: courses) {
			Course course = this.getCourseByName(courseMarks.getCourse()).orElse(new Course());
			Grade grade = new Grade();
			grade.setStudent(student);
			grade.setMarks(courseMarks.getMarks());
			grade.setCourse(course);
			course.setName(courseMarks.getCourse());
			course.setStudentMarks(courseMarks.getMarks());
			course.getStudents().add(student);
			student.getCourses().add(course);
			gradeRepository.save(grade);
			courseRepository.save(course);
			studentRepository.save(student);
		}
	}

	private Optional<Course> getCourseByName(String course) {
		return courseRepository.findByName(course);
	}

	public List<Student> getAllStudentsByCourse(String course) {
		return studentRepository.findAllByCourses_name(course);
	}
	 public void saveCourse(Course course) {
	 	// TODO Auto-generated method stub
	 	courseRepository.save(course);
	 }


	public List<String> getCoursesNameByStudentId(int id) {
		// TODO Auto-generated method stub
		return courseRepository.getCoursesNameByStudentID(id) ;
	}
}
