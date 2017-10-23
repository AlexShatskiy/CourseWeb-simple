package com.sh.course.dao.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.exception.ConnectionPoolException;

public class CloseManager {
	
	private static final Logger log = LogManager.getRootLogger();
	
	public static void closeConnect(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws ConnectionPoolException{
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
}
