package com.sh.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.UserDAO;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.User;
import com.sh.course.domain.parameter.Role;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class SQLUserDAO implements UserDAO {
	private static final Logger log = LogManager.getRootLogger();
	
	private static final String SIGN_IN = "SELECT id, email, nickname, role FROM user WHERE email = ? AND password = ?"; 
	private static final String CHECK_IN = "INSERT INTO user (email, password, nickname, role) VALUES (?, ?, ?, ?);"; 
	private static final String GET_PASSWORD = "SELECT password FROM user WHERE email = ?"; 
	private static final String HAS_EMAIL = "SELECT id FROM user WHERE email = ?"; 
	private static final String HAS_NICKNAME = "SELECT id FROM user WHERE nickname = ?"; 
	private static final String GET_USER_ID = "SELECT id FROM user WHERE email = ?";

	@Override
	public User signIn(String email, String password) throws ConnectionPoolException {
		User user = null;

		int userIdSQL = 0;
		String emailSQL = null;
		String nicknameSQL = null;
		Role roleSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(SIGN_IN);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userIdSQL = resultSet.getInt(1);
				emailSQL = resultSet.getString(2);
				nicknameSQL = resultSet.getString(3);
				roleSQL = Role.valueOf(resultSet.getString(4));
			}

			if (emailSQL != null && nicknameSQL != null && roleSQL != null) {
				user = new User(userIdSQL, emailSQL, nicknameSQL, roleSQL);
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in signIn(String email, String password)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public User checkIn(String email, String password, String nickname) throws ConnectionPoolException {		
		
		User user = null;
		int userId = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(CHECK_IN);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, nickname);
			preparedStatement.setString(4, Role.USER.name());

			preparedStatement.executeUpdate();

			userId = getUserId(email);
			user = new User(userId, email, nickname, Role.USER);
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in checkIn(String email, String password, String nickname)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public boolean hasEmail(String email) throws ConnectionPoolException {

		boolean isHasEmail = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_EMAIL);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasEmail = true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in hasEmail(String email)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return isHasEmail;
	}

	@Override
	public boolean hasNickname(String nickname) throws ConnectionPoolException {

		boolean isHasNickname = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_NICKNAME);
			preparedStatement.setString(1, nickname);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasNickname = true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in hasNickname(String nickname)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return isHasNickname;
	}

	@Override
	public String getPassword(String email) throws ConnectionPoolException {
		String passwordSQL = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_PASSWORD);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				passwordSQL = resultSet.getString(1);
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getPassword(String email)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return passwordSQL;
	}

	@Override
	public int getUserId(String email) throws ConnectionPoolException {

		int userIdSQL = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_USER_ID);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userIdSQL = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getUserId(String email)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return userIdSQL;
	}
}
