package com.codingninjas.CollegeManagementAPI.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingninjas.CollegeManagementAPI.Exception.GradeObjAlreadyExistsException;
import com.codingninjas.CollegeManagementAPI.Exception.StuCourseLinkNotFoundException;
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
	 * 1. Write a method with name "getCoursesNameByStudentId" which takes "int id"
	 * as parameter and returns the list of string containing the name of all the
	 * courses enrolled by the given student id.
	 * 
	 * public List<String> getCoursesNameByStudentId(int id) {
	 * 
	 * }
	 */
	public double getAverageGradesOfStudent(int id) {
		return gradeRepository.getAverageGradesOfStudent(id);
	}

	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public void setStudent(Student student) {
		Set<Course> oldCourseList = student.getCourses();
		Set<Course> newCourseList = new HashSet<>();
		for (Course course : oldCourseList) {
			Course oldCourse = getCourseById(course.getId());
			newCourseList.add(oldCourse);
		}
		student.setCourses(newCourseList);
		studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Course getCourseById(int id) {
		return courseRepository.findById(id).get();
	}

	public void setCourses(Course course, int id) {
		Student student = this.getStudentById(id);
		Course managedCourse = getCourseById(course.getId()); // to get managed entity as course coming form req body is
																// transient and only id is given
		student.getCourses().add(course);
		studentRepository.save(student);
	}

	public void setGradeOfStuForACourse(Grade grade, int studId, int courseId) {
		Student student = getStudentById(studId);
		Course course = getCourseById(courseId);
		if (!student.getCourses().contains(course))
			throw new StuCourseLinkNotFoundException("The Student has not registered for this course");// it fails in
																										// some
																										// cases,when
																										// both course
																										// entities are
																										// from diff
																										// transactin
																										// etc
		if (gradeRepository.existsByStudentIdAndCourseId(studId, courseId)) {
	        throw new GradeObjAlreadyExistsException(
	            "The grade for this student for this course is already entered"
	        );
	    }

		grade.setStudent(student);
		grade.setCourse(course);
		gradeRepository.save(grade);
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
		return courseRepository.getCoursesNameByStudentID(id);
	}
}
