package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Course;
import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;


public interface CourseService {

	void addCourse(String title, String content) throws ServiceException, ServiceExceptionInvalidParameter;
	void addLecturerCourse(int lecturerId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	void deleteLecturerCourse(int lecturerId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	
	List<Course> getAllCourse() throws ServiceException;
	List<Course> getAvailableCourse() throws ServiceException;
	List<Course> getAllCourseLecturer(int lecturerId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getAllLecturerCourse(int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
	List<Course> searchAvailableCourse(String titleOrContent) throws ServiceException, ServiceExceptionInvalidParameter;
	
	boolean hasCourseTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasCourseLecturer(int lecturerId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter;
}
