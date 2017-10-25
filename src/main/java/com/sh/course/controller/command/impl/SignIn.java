package com.sh.course.controller.command.impl;

import java.io.IOException;

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
import com.sh.course.service.UserService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class SignIn implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email;
		String password;
		User user;
		String page;

		email = request.getParameter(PageParameter.EMAIL);
		password = request.getParameter(PageParameter.PASSWORD);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			user = userService.signIn(email, password);
			if (user == null) {
				page = PageLibrary.SIGN_IN_PAGE;

				if (userService.hasEmail(email)) {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Wrong password " + email);
				} else {
					request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "No user " + email);
				}

			} else {

				HttpSession session = request.getSession(true);
				session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
				session.setAttribute(SessionAttribute.ROLE, user.getRole());
				session.setAttribute(SessionAttribute.NICKNAME, user.getNickname());

				if (Role.LECTURER.equals(user.getRole())) {
					page = PageLibrary.LECTURER_PROFILE;
				} else {
					page = PageLibrary.USER_PROFILE;
				}
			}
		} catch (ServiceException e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
			page = PageLibrary.INDEX;
		} catch (ServiceExceptionInvalidParameter e) {
			log.error(e);
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
			page = PageLibrary.INDEX;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
