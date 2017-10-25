package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Course;
import com.sh.course.domain.Diploma;
import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;

public interface DiplomaService {
	
	void enrollForCourse(int userId, int courseId, int lecturerId) throws ServiceException, ServiceExceptionInvalidParameter;
	void rateStudent(int userId, int courseId, int lecturerId, String comment, int rating) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<User> getStudentStudy(int lecturerId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getStudentFinish(int lecturerId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<Course> getCourseStudy(int userId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<Course> getCourseFinish(int userId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	Diploma getDiplomaCourse(int userId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasDiplomaCourse(int userId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
}
