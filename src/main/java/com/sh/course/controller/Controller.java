package com.sh.course.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.PageParameter;
import com.sh.course.controller.command.provider.CommandProvider;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;

public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getRootLogger();

	private static final CommandProvider provider = new CommandProvider();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	@Override
	public void destroy() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.destroyConnectionPool();
		} catch (ConnectionPoolException e) {
			log.error(e);
		}
	}

	@Override
	public void init() throws ServletException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error(e);
		}
	}
}
