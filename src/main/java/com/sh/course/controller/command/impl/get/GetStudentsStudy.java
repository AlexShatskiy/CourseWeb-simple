package com.sh.course.controller.command.impl.get;

import java.io.IOException;
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
import com.sh.course.service.DiplomaService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class GetStudentsStudy implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> students;
		String lecturerId;
		String courseId;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		DiplomaService diplomaService = factory.getDiplomaService();
		
		HttpSession session = request.getSession(true);

		lecturerId = (String) session.getAttribute(SessionAttribute.USER_ID);
		courseId = request.getParameter(PageParameter.COURSE_ID);
		
		try {
			students = diplomaService.getStudentStudy(lecturerId, courseId);
			request.setAttribute(PageSetAttribute.STUDENTS_STUDY, students);
			request.setAttribute(PageSetAttribute.COURSE_ID, courseId);
			request.setAttribute(PageSetAttribute.TITLE, request.getParameter(PageParameter.TITLE));
		} catch (ServiceException | ServiceExceptionInvalidParameter e) {
			log.error(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.LECTURER_PROFILE);
		dispatcher.forward(request, response);
	}
}
