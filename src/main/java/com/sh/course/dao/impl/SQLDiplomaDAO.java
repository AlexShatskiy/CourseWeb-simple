package com.sh.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.DiplomaDAO;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.dao.exception.DaoException;
import com.sh.course.domain.Course;
import com.sh.course.domain.Diploma;
import com.sh.course.domain.User;
import com.sh.course.domain.parameter.DiplomaStatus;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class SQLDiplomaDAO implements DiplomaDAO {
	private static final Logger log = LogManager.getRootLogger();

	private static final String ENROLL_FOR_COURSE = "INSERT INTO diploma (user_id, course_id, lecturer_id, status) VALUES (?, ?, ?, ?);";
	private static final String SET_RATE_STUDENT = "UPDATE diploma SET comment = ?, rating = ?, status = ? WHERE user_id = ? AND course_id = ? AND lecturer_id = ?";
	private static final String GET_STUDENT_STATUS = "SELECT user_id, nickname FROM diploma INNER JOIN user ON diploma.user_id = user.id WHERE lecturer_id = ? AND course_id = ? AND status = ?";
	private static final String GET_COURSE_STATUS = "SELECT course_id, title, content FROM course INNER JOIN diploma ON diploma.course_id = course.id WHERE user_id = ? AND status = ?";
	private static final String GET_DIPLOMA = "SELECT lecturer_id, comment, rating, status FROM diploma WHERE user_id = ? AND course_id = ?";
	private static final String HES_DIPLOMA = "SELECT course_id FROM diploma WHERE user_id = ? AND course_id = ?";	
	
	private static final int RESULT_SUCCESS = 1;
	
	@Override
	public void enrollForCourse(Diploma diploma) throws DaoException, ConnectionPoolException {

		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(ENROLL_FOR_COURSE);

			preparedStatement.setInt(1, diploma.getUserId());
			preparedStatement.setInt(2, diploma.getCourseId());
			preparedStatement.setInt(3, diploma.getLecturerId());
			preparedStatement.setString(4, DiplomaStatus.STUDY.name());

			result = preparedStatement.executeUpdate();
			if (result != RESULT_SUCCESS) {
				throw new DaoException("fail in enrollForCourse(Diploma diploma)");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in enrollForCourse(Diploma diploma)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public void rateStudent(Diploma diploma) throws DaoException, ConnectionPoolException {
		
		int result = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(SET_RATE_STUDENT);

			preparedStatement.setString(1, diploma.getComment());
			preparedStatement.setInt(2, diploma.getRating());
			preparedStatement.setString(3, DiplomaStatus.FINISH.name());
			preparedStatement.setInt(4, diploma.getUserId());
			preparedStatement.setInt(5, diploma.getCourseId());
			preparedStatement.setInt(6, diploma.getLecturerId());
			
			result = preparedStatement.executeUpdate();
			if (result != RESULT_SUCCESS) {
				throw new DaoException("fail in rateStudent(Diploma diploma)");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in rateStudent(Diploma diploma)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public List<User> getStudentStudy(int lecturerId, int courseId) throws ConnectionPoolException {
		List<User> students = new ArrayList<>();

		Integer studentId = null;
		String nickname = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_STUDENT_STATUS);
			preparedStatement.setInt(1, lecturerId);
			preparedStatement.setInt(2, courseId);
			preparedStatement.setString(3, DiplomaStatus.STUDY.name());
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				studentId = resultSet.getInt(1);
				nickname = resultSet.getString(2);

				students.add(new User(studentId, nickname));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getStudentStudy(int lecturerId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return students;
	}

	@Override
	public List<User> getStudentFinish(int lecturerId, int courseId) throws ConnectionPoolException {
		List<User> students = new ArrayList<>();

		Integer studentId = null;
		String nickname = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_STUDENT_STATUS);
			preparedStatement.setInt(1, lecturerId);
			preparedStatement.setInt(2, courseId);
			preparedStatement.setString(3, DiplomaStatus.FINISH.name());
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				studentId = resultSet.getInt(1);
				nickname = resultSet.getString(2);

				students.add(new User(studentId, nickname));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getStudentFinish(int lecturerId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return students;
	}

	@Override
	public List<Course> getCourseStudy(int userId) throws ConnectionPoolException {
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
			preparedStatement = connection.prepareStatement(GET_COURSE_STATUS);

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, DiplomaStatus.STUDY.name());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getCourseStudy(int userId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public List<Course> getCourseFinish(int userId) throws ConnectionPoolException {
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
			preparedStatement = connection.prepareStatement(GET_COURSE_STATUS);

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, DiplomaStatus.FINISH.name());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				courseId = resultSet.getInt(1);
				title = resultSet.getString(2);
				content = resultSet.getString(3);

				courses.add(new Course(courseId, title, content));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getCourseFinish(int userId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return courses;
	}

	@Override
	public Diploma getDiplomaCourse(int userId, int courseId) throws ConnectionPoolException {

		Diploma diploma = null;
		
		Integer lecturerId = null;
		String comment = null;
		Integer rating = null;
		DiplomaStatus status = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();

		try {
			preparedStatement = connection.prepareStatement(GET_DIPLOMA);

			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, courseId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				lecturerId = resultSet.getInt(1);
				comment = resultSet.getString(2);
				rating = resultSet.getInt(3);
				status = DiplomaStatus.valueOf(resultSet.getString(4));

				diploma = new Diploma(userId, courseId, lecturerId, comment, rating, status);
			}
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in getDiplomaCourse(int userId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return diploma;
	}

	@Override
	public boolean hasDiplomaCourse(int userId, int courseId) throws ConnectionPoolException {
		
		boolean isHasDiplomaCourse = false;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ConnectionPool pool = ConnectionPool.getInstance();
		connection = pool.takeConnection();
		try {
			preparedStatement = connection.prepareStatement(HES_DIPLOMA);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, courseId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isHasDiplomaCourse = true;
			}

		} catch (SQLException e) {
			log.error(e);
			throw new ConnectionPoolException("fail in hasDiplomaCourse(int userId, int courseId)", e);
		} finally {
			ConnectionPool.closeConnect(connection, preparedStatement, resultSet);
		}
		return isHasDiplomaCourse;
	}
}
