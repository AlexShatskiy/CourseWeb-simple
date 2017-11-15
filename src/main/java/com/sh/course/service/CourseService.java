package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Course;
import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionHas;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;

/**
 * Сhecks the parameters and passes control to the dao layer
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface CourseService {

	void addCourse(String title, String content) throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas;
	void addLecturerCourse(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas;
	void deleteLecturerCourse(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas;
	
	List<Course> getAllCourse() throws ServiceException;
	List<Course> getAvailableCourse() throws ServiceException;
	List<Course> getAllCourseLecturer(String lecturerId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getAllLecturerCourse(String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<Course> searchAvailableCourse(String titleOrContent) throws ServiceException, ServiceExceptionInvalidParameter;
	
	boolean hasCourseTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasCourseLecturer(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
}
