package com.sh.course.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.CourseDAO;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.dao.factory.DAOFactory;
import com.sh.course.domain.Course;
import com.sh.course.service.CourseService;
import com.sh.course.service.PatentService;
import com.sh.course.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void addBook(String title, String content, int userId) throws ServiceException {
		
		Course course = null;
		
		course = new Course();
		course.setTitle(title);
		course.setContent(content);
		course.setUserId(userId);
		
		DAOFactory factory = DAOFactory.getInstance();
		CourseDAO courseDAO = factory.getCourseDAO();
		
		try {
			courseDAO.addCourse(course);
		} catch (ConnectionPoolException | DaoException e) {
			log.error(e);
			throw new ServiceException("",e);
		}
	}

	@Override
	public void deleteBook(int courseId, String title, String content) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getCourseForTitle(String title) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourseForAuthor(String author) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourseForUserId(int userId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
