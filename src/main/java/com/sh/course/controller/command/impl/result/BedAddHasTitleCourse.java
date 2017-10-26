package com.sh.course.controller.command.impl.result;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageSetAttribute;

public class BedAddHasTitleCourse implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "This course is already there");
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.ADD_COURSE_PAGE);
		dispatcher.forward(request, response);
	}
}
