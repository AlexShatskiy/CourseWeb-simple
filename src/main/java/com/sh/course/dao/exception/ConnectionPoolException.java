package com.sh.course.dao.exception;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
