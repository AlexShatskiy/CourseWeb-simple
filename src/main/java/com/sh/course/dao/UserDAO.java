package com.sh.course.dao;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.User;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface UserDAO {
	
	/**
	 * check email and password in database
	 * @param email
	 * @param password
	 */
	User signIn(String email, String password) throws ConnectionPoolException;
	
	/**
	 * check in site
	 * @param email
	 * @param password
	 * @param nickname
	 */
	User checkIn(String email, String password, String nickname) throws ConnectionPoolException;
	
	/**
	 * return password
	 * @param email
	 */
	String getPassword(String email) throws ConnectionPoolException;
	
	/**
	 * return id
	 * @param email
	 */
	int getUserId(String email) throws ConnectionPoolException;
	
	/**
	 * checks email
	 * @param email
	 */
	boolean hasEmail(String email) throws ConnectionPoolException;
	
	/**
	 * checks nickname
	 * @param nickname
	 */
	boolean hasNickname(String nickname) throws ConnectionPoolException;
}
