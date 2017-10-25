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
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService {
	
	private static final Logger log = LogManager.getRootLogger();
/*
	@Override
	public void addCourse(String title, String content) throws ServiceException {
		
		Course course = null;
		
		course = new Course();
		course.setTitle(title);
		course.setContent(content);

		
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
	public void deleteCourse(int courseId, String title, String content) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
*/


}
