package com.sh.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.CourseDAO;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.parameter.CourseStatus;

public class SQLCourseDAO implements CourseDAO {
	
	private static final Logger log = LogManager.getRootLogger();
	
	private static final String ADD_COURSE = "INSERT INTO course (title, content, status, user_id) VALUES (?, ?, ?, ?);";
	
	private static final int BOOK_ADDED_RESULT = 1;

	@Override
	public void addCourse(Course course) throws ConnectionPoolException, DaoException {
		
		int result = 0;

		int userId = course.getUserId();
		String title = course.getTitle();
		String content = course.getContent();
		String status = CourseStatus.ADDED.name();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(ADD_COURSE);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setString(3, status);
			preparedStatement.setInt(4, userId);

			result = preparedStatement.executeUpdate();
			
			if (result != BOOK_ADDED_RESULT) {
				throw new DaoException("");
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
	}

	@Override
	public  void deleteCourse(Course book) throws ConnectionPoolException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Course> getCourseForTitle(String title) throws ConnectionPoolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourseForAuthor(String author) throws ConnectionPoolException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourseForUserId(int userId) throws ConnectionPoolException {
		// TODO Auto-generated method stub
		return null;
	}
}
