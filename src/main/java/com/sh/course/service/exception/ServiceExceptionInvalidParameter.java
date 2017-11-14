package com.sh.course.service.exception;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class ServiceExceptionInvalidParameter extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceExceptionInvalidParameter() {
		super();
	}

	public ServiceExceptionInvalidParameter(String message) {
		super(message);
	}

	public ServiceExceptionInvalidParameter(Exception e) {
		super(e);
	}

	public ServiceExceptionInvalidParameter(String message, Exception e) {
		super(message, e);
	}
}
