package com.sh.course.controller.command.impl.get;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.PageSetAttribute;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.domain.Course;
import com.sh.course.domain.parameter.Role;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class SearcAvailableCourses implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Course> courses = new ArrayList<>();
		
		String text;
		String page;
		Role role;

		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);
		
		if (Role.LECTURER.equals(role)) {
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			page = PageLibrary.USER_PROFILE;
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();
		
		text = request.getParameter(PageParameter.TITLE_OR_CONTENT);
		
		try {
			courses = courseService.searchAvailableCourse(text);
			request.setAttribute(PageSetAttribute.COURSES_AVAILABLE, courses);
		} catch (ServiceException e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "invalid parameter");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
