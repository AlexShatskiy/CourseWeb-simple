package com.sh.course.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface Command
 * @author Shatskiy Alex
 * @version 1.0
 */
public interface Command {
	
	/**
	 * executes the command
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
