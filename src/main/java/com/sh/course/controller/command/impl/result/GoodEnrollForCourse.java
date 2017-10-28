package com.sh.course.controller.command.impl.result;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageSetAttribute;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.domain.parameter.Role;

public class GoodEnrollForCourse implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(PageSetAttribute.MESSAGE, "You signed up for a course");
		
		Role role;
		String page;

		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);
		
		if (Role.LECTURER.equals(role)) {
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			page = PageLibrary.USER_PROFILE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
