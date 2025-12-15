package com.codingninjas.CollegeManagementAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingninjas.CollegeManagementAPI.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	Optional<Course> findByName(String course);

//	derived query for the below custom query
//	findNameByStudents_Id(int studentId)
	@Query("SELECT c.name FROM Student s JOIN s.courses c WHERE s.id = :id")
	List<String> getCoursesNameByStudentID(int id);

	/*
	 * Write a JPQL Query which returns the List of courses_name by the student id'
	 */
}
