package com.sh.course.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.parameter.PageSetAttribute;
import com.sh.course.service.UserService;
import com.sh.course.service.exception.ServiceException;
import com.sh.course.service.exception.ServiceExceptionInvalidParameter;
import com.sh.course.service.factory.ServiceFactory;

public class PasswordRecovery implements Command {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email;
		String page;

		email = request.getParameter(PageParameter.EMAIL);

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		try {
			
			if (userService.sendPassword(email)){
				request.setAttribute(PageSetAttribute.MESSAGE, "password send");
				page = PageLibrary.INDEX;
			} else if (userService.hasEmail(email)){
				request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry, we will send later");
				page = PageLibrary.INDEX;
			} else {
				request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "There is no such email");
				page = PageLibrary.PASSWORD_RECOVERY;
			}

		} catch (ServiceException e) {
			log.error("ServiceException in PasswordRecovery");
			page = PageLibrary.INDEX;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "sorry fail");
		} catch (ServiceExceptionInvalidParameter e) {
			log.error("ServiceExceptionInvalidParameter in PasswordRecovery");
			page = PageLibrary.PASSWORD_RECOVERY;
			request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Invalid Parameter");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
