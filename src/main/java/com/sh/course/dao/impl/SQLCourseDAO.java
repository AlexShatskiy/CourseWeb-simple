package com.sh.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.CourseDAO;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.User;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class SQLCourseDAO implements CourseDAO {

	private static final Logger log = LogManager.getRootLogger();

	private static final String ADD_COURSE = "INSERT INTO course (title, content) VALUES (?, ?);";
	private static final String ADD_LECTURER_COURSE = "INSERT INTO user_course (user_id, course_id) VALUES (?, ?);";
	private static final String DELETE_LECTURER_COURSE = "DELETE FROM user_course WHERE user_id = ? and course_id = ?";

	private static final String GET_ALL_COURSE = "SELECT id, title, content FROM course";
	private static final String GET_AVAILABLE_COURSE = "SELECT DISTINCT course_id, title, content FROM user_course INNER JOIN course ON user_course.course_id = course.id";
	private static final String GET_ALL_COURSE_LECTURER = "SELECT DISTINCT course_id, title, content FROM user_course INNER JOIN course ON user_course.course_id = course.id WHERE user_id = ?";
	private static final String GET_ALL_LECTURER_COURSE = "SELECT DISTINCT user_id, nickname FROM user_course INNER JOIN user ON user_course.user_id = user.id WHERE course_id = ?";
	private static final String SEARCH_AVAILABLE_COURSE = "SELECT DISTINCT course_id, title, content FROM user_course INNER JOIN course ON user_course.course_id = course.id where title LIKE ?  OR content LIKE ?";

	private static final String HAS_TITLE = "SELECT id FROM course WHERE title = ?";
	private static final String HAS_COURSE = "SELECT user_id FROM user_course WHERE user_id = ? AND course_id = ?";

	private static final int RESULT_SUCCESS = 1;

	@Override
	public void addCourse(Course course) throws ConnectionPoolException, DaoException {

		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(ADD_COURSE);
			
			preparedStatement.setString(1, course.getTitle());
			preparedStatement.setString(2, course.getContent());

			result = preparedStatement.executeUpdate();
			if (result != RESULT_SUCCESS) {
				throw new DaoException("fail in addCourse(Course course)");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in addCourse(Course course)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void addLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException {
		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(ADD_LECTURER_COURSE);

			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, courseId);

			result = preparedStatement.executeUpdate();
			if (result != RESULT_SUCCESS) {
				throw new DaoException("fail in addLecturerCourse(int userId, int courseId)");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in addLecturerCourse(int userId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void deleteLecturerCourse(int userId, int courseId) throws ConnectionPoolException, DaoException {
		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(DELETE_LECTURER_COURSE);

			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, courseId);

			result = preparedStatement.executeUpdate();
			if (result != RESULT_SUCCESS) {
				throw new DaoException("fail in deleteLecturerCourse(int userId, int courseId)");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in deleteLecturerCourse(int userId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<Course> getAllCourse() throws ConnectionPoolException {
		List<Course> courses = new ArrayList<>();

		Integer courseId = null;
		String title = null;
		String content = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_COURSE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getAllCourse()", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public List<Course> getAvailableCourse() throws ConnectionPoolException {
		List<Course> courses = new ArrayList<>();

		Integer courseId = null;
		String title = null;
		String content = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_AVAILABLE_COURSE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getAvailableCourse()", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public List<Course> getAllCourseLecturer(int lecturerId) throws ConnectionPoolException {
		List<Course> courses = new ArrayList<>();

		Integer courseId = null;
		String title = null;
		String content = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_COURSE_LECTURER);
			preparedStatement.setInt(1, lecturerId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getAllCourseLecturer(int lecturerId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public List<Course> searchAvailableCourse(String titleOrContent) throws ConnectionPoolException {
		List<Course> courses = new ArrayList<>();

		Integer courseId = null;
		String title = null;
		String content = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SEARCH_AVAILABLE_COURSE);

			preparedStatement.setString(1, "%" + titleOrContent + "%");
			preparedStatement.setString(2, "%" + titleOrContent + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in searchAvailableCourse(String titleOrContent)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public boolean hasCourseTitle(String title) throws ConnectionPoolException {
		boolean isHasCourseTitle = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_TITLE);
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasCourseTitle = true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in hasCourseTitle(String title)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return isHasCourseTitle;
	}

	@Override
	public boolean hasCourseLecturer(int lecturerId, int courseId) throws ConnectionPoolException {

		boolean isHasCourseTitle = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HAS_COURSE);
			preparedStatement.setInt(1, lecturerId);
			preparedStatement.setInt(2, courseId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasCourseTitle = true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in hasCourseLecturer(int lecturerId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return isHasCourseTitle;
	}

	@Override
	public List<User> getAllLecturerCourse(int courseId) throws ConnectionPoolException {
		List<User> lecturers = new ArrayList<>();

		Integer lecturerId = null;
		String nickname = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_ALL_LECTURER_COURSE);
			preparedStatement.setInt(1, courseId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				lecturerId = resultSet.getInt(1);
				nickname = resultSet.getString(2);

				lecturers.add(new User(lecturerId, nickname));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getAllLecturerCourse(int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return lecturers;
	}
}
