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
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class GetCoursesFinish implements Command {

	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Course> courses = new ArrayList<>();

		Role role;
		String page;
		String id;

		ServiceFactory factory = ServiceFactory.getInstance();
		DiplomaService diplomaService = factory.getDiplomaService();

		HttpSession session = request.getSession(true);
		role = (Role) session.getAttribute(SessionAttribute.ROLE);
		id = (String) session.getAttribute(SessionAttribute.USER_ID);

		if (Role.LECTURER.equals(role)) {
			page = PageLibrary.LECTURER_PROFILE;
		} else {
			page = PageLibrary.USER_PROFILE;
		}

		try {
			courses = diplomaService.getCourseFinish(id);
			request.setAttribute(PageSetAttribute.COURSES_DIPLOMA, courses);
		} catch (ServiceException | ServiceExceptionInvalidParameter e) {
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Fail");
			log.error(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
