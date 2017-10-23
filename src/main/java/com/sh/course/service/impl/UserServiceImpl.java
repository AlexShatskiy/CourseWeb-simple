package com.sh.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.service.UserService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.validator.ParameterValidator;
import com.sh.course.dao.UserDAO;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.factory.DAOFactory;
import com.sh.course.domain.User;


public class UserServiceImpl implements UserService{
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public User signIn(String email, String password) throws ServiceException, ServiceExceptionInvalidParameter {

		User user = null;

		if (!ParameterValidator.isEmailValid(email) || !ParameterValidator.isPasswordValid(password)) {
			throw new ServiceExceptionInvalidParameter();
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			user = userDAO.signIn(email, password);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public User checkIn(String email, String password, String nickname)
			throws ServiceException, ServiceExceptionInvalidParameter {

		User user = null;

		if (!ParameterValidator.isEmailValid(email) || !ParameterValidator.isPasswordValid(password) || !ParameterValidator.isNicknameValid(nickname)) {
			System.out.println("dfdfd");
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			user = userDAO.checkIn(email, password, nickname);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean hasEmail(String email) throws ServiceException, ServiceExceptionInvalidParameter {

		boolean isHasEmail = false;

		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			isHasEmail = userDAO.hasEmail(email);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isHasEmail;
	}

	@Override
	public boolean hasNickname(String nickname) throws ServiceException, ServiceExceptionInvalidParameter {
		boolean isHasNickname = false;

		if (!ParameterValidator.isNicknameValid(nickname)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			isHasNickname = userDAO.hasNickname(nickname);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isHasNickname;
	}

	@Override
	public boolean sendPassword(String email) throws ServiceException, ServiceExceptionInvalidParameter {
		boolean isSend = false;
		
		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}
		// TODO 
		
		return isSend;
	}

	@Override
	public int getUserId(String email) throws ServiceException, ServiceExceptionInvalidParameter {
		int userId = 0;

		if (!ParameterValidator.isEmailValid(email)) {
			throw new ServiceExceptionInvalidParameter("Not valid parameters in UserServiceImpl");
		}

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		try {
			userId = userDAO.getUserId(email);
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return userId;
	}

	@Override
	public List<User> getAllUser() throws ServiceException {
		List<User> users = new ArrayList<>();
		
		// TODO 
		return users;
	}
	

}
