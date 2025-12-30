package com.codingninjas.CollegeManagementAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingninjas.CollegeManagementAPI.entities.Grade;
import com.codingninjas.CollegeManagementAPI.entities.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	@Query(value="SELECT AVG(marks) FROM Grade WHERE student_id=:id",nativeQuery=true)
	public double getAverageGradesOfStudent(int id);
}
