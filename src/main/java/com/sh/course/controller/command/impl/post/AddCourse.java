package com.sh.course.controller.command.impl.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.service.CourseService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class AddCourse implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title;
		String content;

		ServiceFactory factory = ServiceFactory.getInstance();
		CourseService courseService = factory.getCourseService();

		title = request.getParameter(PageParameter.TITLE);
		content = request.getParameter(PageParameter.CONTENT);

		try {
			courseService.addCourse(title, content);
			response.sendRedirect(PageLibrary.URL_GOOD_ADD_COURSE);
		} catch (ServiceException e) {
			try {
				if (courseService.hasCourseTitle(title)){
					response.sendRedirect(PageLibrary.URL_BED_ADD_HAS_TITLE_COURSE);
				} else {
					response.sendRedirect(PageLibrary.URL_BED_ADD_COURSE);
				}
			} catch (ServiceException | ServiceExceptionInvalidParameter e1) {
				log.error(e);
				response.sendRedirect(PageLibrary.URL_BED_ADD_COURSE);
			}
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_ADD_INVALID_COURSE);
		}
	}
}