package com.sh.course.dao;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.Diploma;
import com.sh.course.domain.User;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface DiplomaDAO {
	
	/**
	 * enroll for course in database
	 * @param diploma
	 */
	void enrollForCourse(Diploma diploma) throws DaoException, ConnectionPoolException;
	
	/**
	 * rate student in database
	 * @param diploma
	 */
	void rateStudent(Diploma diploma) throws DaoException, ConnectionPoolException;
	
	/**
	 * return students study course
	 * @param lecturerId
	 * @param courseId
	 */
	List<User> getStudentStudy(int lecturerId, int courseId) throws ConnectionPoolException;
	
	/**
	 * return students finish course
	 * @param lecturerId
	 * @param courseId
	 */
	List<User> getStudentFinish(int lecturerId, int courseId) throws ConnectionPoolException;
	
	/**
	 * return courses study
	 * @param userId
	 */
	List<Course> getCourseStudy(int userId) throws ConnectionPoolException;
	
	/**
	 * return courses finish
	 * @param userId
	 */
	List<Course> getCourseFinish(int userId) throws ConnectionPoolException;
	
	/**
	 * return diploma
	 * @param userId
	 * @param courseId
	 */
	Diploma getDiplomaCourse(int userId, int courseId) throws ConnectionPoolException;
	
	/**
	 * checks diploma
	 * @param userId
	 * @param courseId
	 */
	boolean hasDiplomaCourse(int userId, int courseId) throws ConnectionPoolException;
}
