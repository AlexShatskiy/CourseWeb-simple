package com.sh.course.dao;

import java.util.List;

import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.User;

public interface UserDAO {
	
	User signIn(String email, String password) throws ConnectionPoolException;
	User checkIn(String email, String password, String nickname) throws ConnectionPoolException;
	String getPassword(String email) throws ConnectionPoolException;
	int getUserId(String email) throws ConnectionPoolException;
	List<User> getAllUser(Integer courseId) throws ConnectionPoolException;
	
	boolean hasEmail(String email) throws ConnectionPoolException;
	boolean hasNickname(String nickname) throws ConnectionPoolException;
}
