package com.sh.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.DiplomaDAO;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.dao.factory.DAOFactory;
import com.sh.course.domain.Course;
import com.sh.course.domain.Diploma;
import com.sh.course.domain.User;
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.validator.ParameterValidator;

public class DiplomaServiceImpl implements DiplomaService {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void enrollForCourse(String userId, String courseId, String lecturerId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId) 
				|| !ParameterValidator.isIdValid(lecturerId) || userId == lecturerId) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			diplomaDAO.enrollForCourse(new Diploma(Integer.parseInt(userId), Integer.parseInt(courseId), Integer.parseInt(lecturerId)));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void rateStudent(String userId, String courseId, String lecturerId, String comment, String rating)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId) || !ParameterValidator.isIdValid(lecturerId) || 
				!ParameterValidator.isTextValid(comment) || !ParameterValidator.isRatingValid(rating)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			diplomaDAO.rateStudent(new Diploma(Integer.parseInt(userId), Integer.parseInt(courseId), Integer.parseInt(lecturerId), comment, Integer.parseInt(rating)));
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getStudentStudy(String lecturerId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		List<User> students = new ArrayList<>();

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			students = diplomaDAO.getStudentStudy(Integer.parseInt(lecturerId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return students;
	}

	@Override
	public List<User> getStudentFinish(String lecturerId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		List<User> students = new ArrayList<>();

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(lecturerId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			students = diplomaDAO.getStudentFinish(Integer.parseInt(lecturerId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return students;
	}

	@Override
	public List<Course> getCourseStudy(String userId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<Course> courses = new ArrayList<>();

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courses = diplomaDAO.getCourseStudy(Integer.parseInt(userId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return courses;
	}

	@Override
	public List<Course> getCourseFinish(String userId) throws ServiceException, ServiceExceptionInvalidParameter {

		List<Course> courses = new ArrayList<>();

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			courses = diplomaDAO.getCourseFinish(Integer.parseInt(userId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return courses;
	}

	@Override
	public Diploma getDiplomaCourse(String userId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {

		Diploma diploma = null;

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			diploma = diplomaDAO.getDiplomaCourse(Integer.parseInt(userId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return diploma;
	}

	@Override
	public boolean hasDiplomaCourse(String userId, String courseId)
			throws ServiceException, ServiceExceptionInvalidParameter {
		
		boolean result = false;

		DAOFactory factory = DAOFactory.getInstance();
		DiplomaDAO diplomaDAO = factory.getDiplomaDAO();
		
		if (!ParameterValidator.isIdValid(userId) || !ParameterValidator.isIdValid(courseId)) {
			throw new ServiceExceptionInvalidParameter();
		}
		
		try {
			result = diplomaDAO.hasDiplomaCourse(Integer.parseInt(userId), Integer.parseInt(courseId));
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return result;
	}
}
