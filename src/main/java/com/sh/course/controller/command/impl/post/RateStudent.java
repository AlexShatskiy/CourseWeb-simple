package com.sh.course.controller.command.impl.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.SessionAttribute;
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class RateStudent implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId;
		int courseId;
		int lecturerId;
		
		String comment;
		int rating;
		
		HttpSession session = request.getSession(true);

		ServiceFactory factory = ServiceFactory.getInstance();
		DiplomaService diplomaService = factory.getDiplomaService();
		
		userId = Integer.parseInt(request.getParameter(PageParameter.USER_ID));
		courseId = Integer.parseInt(request.getParameter(PageParameter.COURSE_ID));
		lecturerId = (Integer) session.getAttribute(SessionAttribute.USER_ID);
		comment = request.getParameter(PageParameter.COMMENT);
		rating = Integer.parseInt(request.getParameter(PageParameter.RATING));
		
		try {
			diplomaService.rateStudent(userId, courseId, lecturerId, comment, rating);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceExceptionInvalidParameter e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
