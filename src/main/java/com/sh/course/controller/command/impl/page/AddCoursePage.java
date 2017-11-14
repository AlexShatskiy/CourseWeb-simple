package com.sh.course.controller.command.impl.page;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class AddCoursePage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.ADD_COURSE_PAGE);
		dispatcher.forward(request, response);
	}
}
