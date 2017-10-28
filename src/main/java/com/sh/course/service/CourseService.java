package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Course;
import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;


public interface CourseService {

	void addCourse(String title, String content) throws ServiceException, ServiceExceptionInvalidParameter;
	void addLecturerCourse(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	void deleteLecturerCourse(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<Course> getAllCourse() throws ServiceException;
	List<Course> getAvailableCourse() throws ServiceException;
	List<Course> getAllCourseLecturer(String lecturerId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getAllLecturerCourse(String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<Course> searchAvailableCourse(String titleOrContent) throws ServiceException, ServiceExceptionInvalidParameter;
	
	boolean hasCourseTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasCourseLecturer(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter;
}
