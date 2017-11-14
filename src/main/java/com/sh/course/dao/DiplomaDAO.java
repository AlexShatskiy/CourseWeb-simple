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
	
	void enrollForCourse(Diploma diploma) throws DaoException, ConnectionPoolException;
	void rateStudent(Diploma diploma) throws DaoException, ConnectionPoolException;
	
	List<User> getStudentStudy(int lecturerId, int courseId) throws ConnectionPoolException;
	List<User> getStudentFinish(int lecturerId, int courseId) throws ConnectionPoolException;
	
	List<Course> getCourseStudy(int userId) throws ConnectionPoolException;
	List<Course> getCourseFinish(int userId) throws ConnectionPoolException;
	
	Diploma getDiplomaCourse(int userId, int courseId) throws ConnectionPoolException;
	boolean hasDiplomaCourse(int userId, int courseId) throws ConnectionPoolException;
}
