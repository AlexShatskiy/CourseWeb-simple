package com.sh.course.controller.command.impl.page;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.PageSetAttribute;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class RateStudentPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute(PageSetAttribute.TITLE, request.getParameter(PageParameter.TITLE));
		request.setAttribute(PageSetAttribute.USER_ID, request.getParameter(PageParameter.USER_ID));
		request.setAttribute(PageSetAttribute.COURSE_ID, request.getParameter(PageParameter.COURSE_ID));
		request.setAttribute(PageSetAttribute.NICKNAME, request.getParameter(PageParameter.NICKNAME));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.RATE_STUDENT_PAGE);
		dispatcher.forward(request, response);
	}
}
