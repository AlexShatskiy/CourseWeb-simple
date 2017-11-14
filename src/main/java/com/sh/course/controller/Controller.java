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
import com.sh.course.controller.command.provider.CommandProviderXML;
import com.sh.course.controller.exception.InitDestroyException;
import com.sh.course.dao.connection.ConnectionPool;
import com.sh.course.dao.exception.ConnectionPoolException;

/**
 * handles all get and post requests
 * @author Shatskiy Alex
 * @version 1.0
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getRootLogger();
	/**
	 * attribute - command provider
	 */
	private static final CommandProviderXML provider = new CommandProviderXML();

	/**
	 * handles all get requests
	 * @param request
	 * @param response
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	/**
	 * handles all post requests
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName = request.getParameter(PageParameter.COMMAND);

		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	/**
	 * destroy HttpServlet and ConnectionPool
	 */
	@Override
	public void destroy() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.destroyConnectionPool();
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new InitDestroyException("fail in destroy()", e);
		}
	}

	/**
	 * init HttpServlet and ConnectionPool
	 */
	@Override
	public void init() throws ServletException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			log.error(e);
			throw new InitDestroyException("fail in init()", e);
		}
	}
}
