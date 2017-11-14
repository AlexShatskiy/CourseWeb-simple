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
import com.sh.course.service.exception.ServiceExceptionHas;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class EnrollForCourse implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId;
		String courseId;
		String lecturerId;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		DiplomaService diplomaService = factory.getDiplomaService();
		
		HttpSession session = request.getSession(true);
		
		userId = (String) session.getAttribute(SessionAttribute.USER_ID);
		courseId = request.getParameter(PageParameter.COURSE_ID);
		lecturerId = request.getParameter(PageParameter.LECTURER_ID); 
		
		try {
			diplomaService.enrollForCourse(userId, courseId, lecturerId);
			response.sendRedirect(PageLibrary.URL_GOOD_ENROLL_FOR_COURSE);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_ENROLL_FOR_COURSE);
		} catch (ServiceExceptionHas e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_ENROLL_FOR_COURSE_HAS);
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			response.sendRedirect(PageLibrary.URL_BED_ENROLL_FOR_COURSE);
		}
	}
}
