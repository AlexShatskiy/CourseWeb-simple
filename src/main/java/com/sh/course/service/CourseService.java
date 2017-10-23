package com.sh.course.service;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.Course;
import com.sh.course.service.exception.ServiceException;


public interface CourseService {
	void addBook(String title, String content, int userId) throws ServiceException;
	void deleteBook(int courseId, String title, String content) throws ServiceException;
	List<Course> getCourseForTitle(String title) throws ServiceException;
	List<Course> getCourseForAuthor(String author) throws ServiceException;
	List<Course> getCourseForUserId(int userId) throws ServiceException;
}
