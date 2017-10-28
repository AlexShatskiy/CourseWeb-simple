package com.sh.course.controller.command.impl.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class AddLecturerCourse implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lecturerId;
		String courseId;

		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();

		HttpSession session = request.getSession(true);

		lecturerId = (String) session.getAttribute(SessionAttribute.USER_ID);

		courseId = request.getParameter(PageParameter.COURSE_ID);

		try {
			courseService.addLecturerCourse(lecturerId, courseId);
			response.sendRedirect(PageLibrary.URL_GOOD_ADD_LECTURER_COURSE);
		} catch (ServiceException e) {
			log.error(e);
			try {
				if (courseService.hasCourseLecturer(lecturerId, courseId)){
					response.sendRedirect(PageLibrary.URL_GOOD_ADD_LECTURER_COURSE);
				} else {
					response.sendRedirect(PageLibrary.URL_BED_ADD_LECTURER_COURSE);
				}
			} catch (ServiceException | ServiceExceptionInvalidParameter e1) {
				log.error(e);
				response.sendRedirect(PageLibrary.URL_BED_ADD_LECTURER_COURSE);
			}
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_ADD_LECTURER_COURSE);
		}
	}
}
