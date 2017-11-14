package com.sh.course.service.exception;

public class ServiceExceptionHas extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceExceptionHas() {
		super();
	}

	public ServiceExceptionHas(String message) {
		super(message);
	}

	public ServiceExceptionHas(Exception e) {
		super(e);
	}

	public ServiceExceptionHas(String message, Exception e) {
		super(message, e);
	}
}
