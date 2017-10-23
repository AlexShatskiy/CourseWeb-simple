package com.sh.course.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.UserDAO;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.User;
import com.sh.course.domain.parameter.Role;


public class SQLUserDAO implements UserDAO {
	private static final Logger log = LogManager.getRootLogger();
	
	private static final String SIGN_IN = "SELECT id, email, nickname, role FROM user WHERE email = ? AND password = ?"; 
	private static final String CHECK_IN = "INSERT INTO user (email, password, nickname, role) VALUES (?, ?, ?, ?);"; 
	private static final String GET_PASSWORD = "SELECT password FROM user WHERE email = ?"; 
	private static final String HAS_EMAIL = "SELECT id FROM user WHERE email = ?"; 
	private static final String HAS_NICKNAME = "SELECT id FROM user WHERE nickname = ?"; 
	private static final String GET_USER_ID = "SELECT id FROM user WHERE email = ?";
	private static final String GET_ALL_USER = "SELECT id, email, nickname, role FROM user";

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
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
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
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
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
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
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
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
		}
		return isHasNickname;
	}

	@Override
	public String getPassword(String email) throws ConnectionPoolException {
		String passwordSQL = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			ps = connection.prepareStatement(GET_PASSWORD);
			ps.setString(1, email);
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				passwordSQL = resultSet.getString(1);
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
		}
		return passwordSQL;
	}

	@Override
	public List<User> getAllUser(Integer courseId) throws ConnectionPoolException {

		int user_id;
		String email;
		String nickname;
		Role role;

		List<User> users = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_USER);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user_id = resultSet.getInt(1);
				email = resultSet.getString(2);
				nickname = resultSet.getString(3);
				role = Role.valueOf(resultSet.getString(4));

				User user = new User(user_id, email, nickname, role);
				users.add(user);
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
		}
		return users;
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
			throw new ConnectionPoolException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					log.error(e);
					throw new ConnectionPoolException(e);
				}
			}
		}
		return userIdSQL;
	}
}
