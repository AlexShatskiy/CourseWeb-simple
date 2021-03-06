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
import com.sh.course.domain.User;
import com.sh.course.domain.parameter.Role;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class GetAllLecturersCourse implements Command  {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> lecturers = new ArrayList<>();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();

		String page;
		Role role;
		String courseId;
		
		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);

		courseId = request.getParameter(PageParameter.COURSE_ID);
		
		if (Role.LECTURER.equals(role)) {
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			page = PageLibrary.USER_PROFILE;
		}
		
		try {
			lecturers = courseService.getAllLecturerCourse(courseId);
		} catch (ServiceException e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Sorry you can not see the course lecturers");
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid parameter");
		}

		request.setAttribute(PageSetAttribute.LECTURERS_COURSE, lecturers);
		request.setAttribute(PageSetAttribute.TITLE, request.getParameter(PageParameter.TITLE));
		request.setAttribute(PageSetAttribute.COURSE_ID, request.getParameter(PageParameter.COURSE_ID));

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
