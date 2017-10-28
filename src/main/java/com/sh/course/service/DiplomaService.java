package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Course;
import com.sh.course.domain.Diploma;
import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;

public interface DiplomaService {
	
	void enrollForCourse(String userId, String courseId, String lecturerId) throws ServiceException, ServiceExceptionInvalidParameter;
	void rateStudent(String userId, String courseId, String lecturerId, String comment, String rating) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<User> getStudentStudy(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getStudentFinish(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<Course> getCourseStudy(String userId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<Course> getCourseFinish(String userId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	Diploma getDiplomaCourse(String userId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasDiplomaCourse(String userId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
}
