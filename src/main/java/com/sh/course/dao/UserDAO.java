package com.sh.course.dao;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.User;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface UserDAO {
	
	User signIn(String email, String password) throws ConnectionPoolException;
	User checkIn(String email, String password, String nickname) throws ConnectionPoolException;
	String getPassword(String email) throws ConnectionPoolException;
	int getUserId(String email) throws ConnectionPoolException;
	
	boolean hasEmail(String email) throws ConnectionPoolException;
	boolean hasNickname(String nickname) throws ConnectionPoolException;
}
