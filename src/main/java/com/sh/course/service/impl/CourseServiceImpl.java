package com.sh.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.CourseDAO;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.dao.factory.DAOFactory;
import com.sh.course.domain.Course;
import com.sh.course.domain.User;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.validator.ParameterValidator;

public class CourseServiceImpl implements CourseService {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public List<Course> getAvailableCourse() throws ServiceException {
		List<Course> course = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		try {
			course = courseDAO.getAvailableCourse();
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return course;
	}

	@Override
	public void addCourse(String title, String content) throws ServiceException, ServiceExceptionInvalidParameter {
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isTextValid(title) || !ParameterValidator.isTextValid(content)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courseDAO.addCourse(new Course(title, content));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}	
	}

	@Override
	public void addLecturerCourse(int userId, int courseId) throws ServiceException, ServiceExceptionInvalidParameter {
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courseDAO.addLecturerCourse(userId, courseId);
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteLecturerCourse(int userId, int courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courseDAO.deleteLecturerCourse(userId, courseId);
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Course> getAllCourse() throws ServiceException {
		List<Course> course = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		try {
			course = courseDAO.getAllCourse();
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return course;
	}

	@Override
	public List<Course> getAllCourseLecturer(int lecturerId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<Course> course = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			course = courseDAO.getAllCourseLecturer(lecturerId);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return course;
	}

	@Override
	public List<User> getAllLecturerCourse(int courseId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<User> lecturers = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			lecturers = courseDAO.getAllLecturerCourse(courseId);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return lecturers;
	}

	@Override
	public List<Course> searchAvailableCourse(String titleOrContent)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		List<Course> courses = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isTextValid(titleOrContent)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courses = courseDAO.searchAvailableCourse(titleOrContent);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return courses;
	}

	@Override
	public boolean hasCourseTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean result = false;
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isTextValid(title)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			result = courseDAO.hasCourseTitle(title);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public boolean hasCourseLecturer(int lecturerId, int courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean result = false;
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			result = courseDAO.hasCourseLecturer(lecturerId, courseId);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
}
