package com.codingninjas.CollegeManagementAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingninjas.CollegeManagementAPI.entities.Grade;
import com.codingninjas.CollegeManagementAPI.entities.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
