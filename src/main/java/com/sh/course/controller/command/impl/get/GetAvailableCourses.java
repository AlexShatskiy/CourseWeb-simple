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
import com.sh.course.controller.command.parameter.PageSetAttribute;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.domain.Course;
import com.sh.course.domain.parameter.Role;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.factory.ServiceFactory;

public class GetAvailableCourses implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Course> courses = new ArrayList<>();
		
		Role role;
		String page;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();
		
		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);
		
		if (Role.LECTURER.equals(role)) {
			page = PageLibrary.LECTURER_PROFILE;
		} else if(Role.USER.equals(role)) {
			page = PageLibrary.USER_PROFILE;
		} else {
			page = PageLibrary.INDEX;
		}
		
		try {
			courses = courseService.getAvailableCourse();
			request.setAttribute(PageSetAttribute.COURSES_AVAILABLE, courses);
		} catch (ServiceException e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
