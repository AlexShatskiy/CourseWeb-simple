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

public class GetAllCoursesLecturer implements Command {

	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Course> courses = new ArrayList<>();

		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();

		String page;
		Role role;
		String lecturerId;
		String pageSetAttribute;

		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);
		
		String sessionId = (String) session.getAttribute(SessionAttribute.USER_ID);
		String pageLecturerId = request.getParameter(PageParameter.LECTURER_ID);

		if (Role.LECTURER.equals(role)) {

			if (pageLecturerId == null) {
				lecturerId = sessionId;
				pageSetAttribute = PageSetAttribute.COURSES_LECTURER;
			} else if (sessionId.equals(pageLecturerId)){
				lecturerId = sessionId;
				pageSetAttribute = PageSetAttribute.COURSES_LECTURER;
			} else {
				lecturerId = pageLecturerId;
				pageSetAttribute = PageSetAttribute.COURSES_AVAILABLE;
			}
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			lecturerId = pageLecturerId;
			page = PageLibrary.USER_PROFILE;
			pageSetAttribute = PageSetAttribute.COURSES_AVAILABLE;
		}
		try {
			courses = courseService.getAllCourseLecturer(lecturerId);
		} catch (ServiceException e) {
			log.error(e);
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
		}
		
		request.setAttribute(pageSetAttribute, courses);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
