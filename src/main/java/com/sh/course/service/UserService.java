package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.User;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;

public interface UserService {
	
	User signIn(String email, String password) throws ServiceException, ServiceExceptionInvalidParameter;
	User checkIn(String email, String password, String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasEmail(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean hasNickname(String nickname) throws ServiceException, ServiceExceptionInvalidParameter;
	boolean sendPassword(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	int getUserId(String email) throws ServiceException, ServiceExceptionInvalidParameter;
	List<User> getAllUser() throws ServiceException;
}
