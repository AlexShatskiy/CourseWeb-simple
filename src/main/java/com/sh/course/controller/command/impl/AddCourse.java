package com.sh.course.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.factory.ServiceFactory;


public class AddCourse implements Command {
	
	private static final String URL_GOOD_ADD = "controller?command=PROFILE_PAGE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title;
		String content;
		Integer userId;
		
		HttpSession session = request.getSession(true);
		userId = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService bookService = factory.getCourseService();
		
		title = request.getParameter(PageParameter.TITLE);
		content = request.getParameter(PageParameter.CONTENT);
		
		try {
			bookService.addBook(title, content, userId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(URL_GOOD_ADD);
		
	}
}