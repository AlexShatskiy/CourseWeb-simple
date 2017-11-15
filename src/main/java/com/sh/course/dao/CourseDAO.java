package com.sh.course.dao;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.User;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface CourseDAO {
	
	/**
	 * add course to database
	 * @param course
	 */
	void addCourse(Course course) throws ConnectionPoolException, DaoException;
	
	/**
	 * add lecturer in course to database
	 * @param userId
	 * @param courseId
	 */
	void addLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException;
	
	/**
	 * delete lecturer in course to database
	 * @param userId
	 * @param courseId
	 */
	void deleteLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException;
	
	/**
	 * return all courses
	 */
	List<Course> getAllCourse() throws ConnectionPoolException;

	/**
	 * return all available courses from database
	 */
	List<Course> getAvailableCourse() throws ConnectionPoolException;

	/**
	 * return all courses lecturer from database
	 * @param lecturerId
	 */
	List<Course> getAllCourseLecturer(int lecturerId) throws ConnectionPoolException;

	/**
	 * return all lecturers course from database
	 * @param courseId
	 */
	List<User> getAllLecturerCourse(int courseId) throws ConnectionPoolException;

	/**
	 * add search available course from database
	 * @param titleOrContent
	 */
	List<Course> searchAvailableCourse(String titleOrContent) throws ConnectionPoolException;

	/**
	 * checks title
	 * @param title
	 */
	boolean hasCourseTitle(String title) throws ConnectionPoolException;

	/**
	 * checks lecturer from course
	 * @param lecturerId
	 * @param courseId
	 */
	boolean hasCourseLecturer(int lecturerId, int courseId) throws ConnectionPoolException;
}
