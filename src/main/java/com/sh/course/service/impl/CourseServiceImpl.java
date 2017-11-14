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
import com.sh.course.service.exception.ServiceExceptionHas;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.validator.ParameterValidator;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
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
			throw new ServiceException("fail in getAvailableCourse()", e);
		}
		return course;
	}

	@Override
	public void addCourse(String title, String content) throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas {
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isTextValid(title) || !ParameterValidator.isTextValid(content)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		if (hasCourseTitle(title)){
			throw new ServiceExceptionHas();
		}
		
		try {
			courseDAO.addCourse(new Course(title, content));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException("fail in addCourse(String title, String content)", e);
		}	
	}

	@Override
	public void addLecturerCourse(String lecturerId, String courseId) throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas{
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		if (hasCourseLecturer(lecturerId, courseId)){
			throw new ServiceExceptionHas("fail in addLecturerCourse(String lecturerId, String courseId)");
		}
		
		try {
			courseDAO.addLecturerCourse(Integer.parseInt(lecturerId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException("fail in addLecturerCourse(String lecturerId, String courseId)", e);
		}
	}

	@Override
	public void deleteLecturerCourse(String lecturerId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter, ServiceExceptionHas {
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter("fail in deleteLecturerCourse(String lecturerId, String courseId)");
		}
		
		if (hasCourseLecturer(lecturerId, courseId)){
			throw new ServiceExceptionHas("fail in deleteLecturerCourse(String lecturerId, String courseId)");
		}
		
		try {
			courseDAO.deleteLecturerCourse(Integer.parseInt(lecturerId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException("fail in deleteLecturerCourse(String lecturerId, String courseId)", e);
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
			throw new ServiceException("fail in getAllCourse()", e);
		}
		return course;
	}

	@Override
	public List<Course> getAllCourseLecturer(String lecturerId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<Course> course = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId)) {
			throw new ServiceExceptionInvalidParameter("fail in getAllCourseLecturer(String lecturerId)");
		}
		
		try {
			course = courseDAO.getAllCourseLecturer(Integer.parseInt(lecturerId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException("fail in getAllCourseLecturer(String lecturerId)", e);
		}
		return course;
	}

	@Override
	public List<User> getAllLecturerCourse(String courseId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<User> lecturers = new ArrayList<>();
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter("fail in getAllLecturerCourse(String courseId)");
		}
		
		try {
			lecturers = courseDAO.getAllLecturerCourse(Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException("fail in getAllLecturerCourse(String courseId)", e);
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
			throw new ServiceExceptionInvalidParameter("fail in searchAvailableCourse(String titleOrContent)");
		}
		
		try {
			courses = courseDAO.searchAvailableCourse(titleOrContent);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException("fail in searchAvailableCourse(String titleOrContent)", e);
		}
		return courses;
	}

	@Override
	public boolean hasCourseTitle(String title) throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean result = false;
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isTextValid(title)) {
			throw new ServiceExceptionInvalidParameter("fail in hasCourseTitle(String title)");
		}
		
		try {
			result = courseDAO.hasCourseTitle(title);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException("fail in hasCourseTitle(String title)", e);
		}
		return result;
	}

	@Override
	public boolean hasCourseLecturer(String lecturerId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean result = false;
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter("fail in hasCourseLecturer(String lecturerId, String courseId)");
		}
		
		try {
			result = courseDAO.hasCourseLecturer(Integer.parseInt(lecturerId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException("fail in hasCourseLecturer(String lecturerId, String courseId)", e);
		}
		return result;
	}
}
