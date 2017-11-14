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
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class RateStudent implements Command {

	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId;
		String courseId;
		String lecturerId;

		String comment;
		String rating;

		HttpSession session = request.getSession(true);

		ServiceFactory factory = ServiceFactory.getInstance();
		DiplomaService diplomaService = factory.getDiplomaService();

		userId = request.getParameter(PageParameter.USER_ID);
		courseId = request.getParameter(PageParameter.COURSE_ID);
		lecturerId = (String) session.getAttribute(SessionAttribute.USER_ID);
		comment = request.getParameter(PageParameter.COMMENT);
		rating = request.getParameter(PageParameter.RATING);

		try {
			diplomaService.rateStudent(userId, courseId, lecturerId, comment, rating);
			response.sendRedirect(PageLibrary.URL_GOOD_RATING);
		} catch (ServiceException | ServiceExceptionInvalidParameter e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_RATING);
		}
	}
}
