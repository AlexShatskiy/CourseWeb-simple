package com.sh.course.service;

import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;

/**
 * Сhecks the parameters and passes control to the dao layer
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface UserService {
	
	User signIn(String email, String password) throws ServiceException, ServiceExceptionInvalidParameter;
	User checkIn(String email, String password, String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasEmail(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasNickname(String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean sendPassword(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	int getUserId(String email) throws ServiceException, ServiceExceptionInvalidParameter;
}
