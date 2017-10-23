package com.sh.course.dao;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;


public interface CourseDAO {
	void addCourse(Course course) throws ConnectionPoolException, DaoException;
	void deleteCourse(Course course) throws ConnectionPoolException;
	List<Course> getCourseForTitle(String title) throws ConnectionPoolException;
	List<Course> getCourseForAuthor(String author) throws ConnectionPoolException;
	List<Course> getCourseForUserId(int userId) throws ConnectionPoolException;
}
