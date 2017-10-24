package com.sh.course.dao;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.User;


public interface CourseDAO {
	void addCourse(Course course) throws ConnectionPoolException, DaoException;
	void addLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException;
	void deleteLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException;
	
	List<Course> getAllCourse() throws ConnectionPoolException;
	List<Course> getAvailableCourse() throws ConnectionPoolException;
	List<Course> getAllCourseLecturer(int lecturerId) throws ConnectionPoolException;
	List<User> getAllLecturerCourse(int courseId) throws ConnectionPoolException;
	List<Course> searchAvailableCourse(String titleOrContent) throws ConnectionPoolException;
	
	boolean hasCourseTitle(String title) throws ConnectionPoolException;
	boolean hasCourseLecturer(int lecturerId, int courseId) throws ConnectionPoolException;
}
