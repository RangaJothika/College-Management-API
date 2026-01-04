package com.codingninjas.CollegeManagementAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StuCourseLinkNotFoundException extends RuntimeException {
	public StuCourseLinkNotFoundException(String message) {
		super(message);
	}
}
