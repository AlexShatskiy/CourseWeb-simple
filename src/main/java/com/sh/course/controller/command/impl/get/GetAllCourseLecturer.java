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

public class GetAllCourseLecturer implements Command {

	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Course> courses = new ArrayList<>();

		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();

		String page;
		Role role;
		int lecturerId;

		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);

		if (Role.LECTURER.equals(role)) {

			if (request.getParameter(PageParameter.LECTURER_ID) == null) {
				lecturerId = (Integer) session.getAttribute(SessionAttribute.USER_ID);
			} else {
				lecturerId = Integer.parseInt(request.getParameter(PageParameter.LECTURER_ID));
			}
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			lecturerId = Integer.parseInt(request.getParameter(PageParameter.LECTURER_ID));
			page = PageLibrary.USER_PROFILE;
		}
		try {
			courses = courseService.getAllCourseLecturer(lecturerId);
		} catch (ServiceException e) {
			log.error(e);
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
		}

		request.setAttribute(PageSetAttribute.COURSES_LECTURER, courses);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
