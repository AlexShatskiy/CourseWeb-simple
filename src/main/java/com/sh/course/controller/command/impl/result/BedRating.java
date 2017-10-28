package com.sh.course.controller.command.impl.result;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageLibrary;
import com.sh.course.controller.command.parameter.PageSetAttribute;

public class BedRating implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(PageSetAttribute.ERROR_MESSAGE, "Fail");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLibrary.LECTURER_PROFILE);
		dispatcher.forward(request, response);
	}
}
