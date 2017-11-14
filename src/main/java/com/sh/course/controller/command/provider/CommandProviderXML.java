package com.sh.course.controller.command.provider;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.parameter.CommandTagName;
import com.sh.course.controller.exception.ControllerException;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public class CommandProviderXML {
	
	private static final Logger log = LogManager.getRootLogger();
	
	private final static String PASS = "/commands.xml";
	private Map<String, Command> commands;

	public CommandProviderXML() {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(PASS);

		try {
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			commands = getCommandsMap(reader);
		} catch (XMLStreamException e) {
			log.error(e);
			throw new ControllerException("fail in CommandProviderXML()", e);
		}
	}

	public Command getCommand(String commandName) {

		return commands.get(commandName);
	}

	private static Map<String, Command> getCommandsMap(XMLStreamReader reader) throws XMLStreamException {

		Map<String, Command> commands = new HashMap<>();
		CommandTagName elementName = null;

		String key = null;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = CommandTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case COMMAND:
					break;
				default:
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();

				if (text.isEmpty()) {
					break;
				}

				switch (elementName) {

				case NAME:
					key = text;
					break;
				case REALIZATION:
					Command command = commandCreator(text);
					commands.put(key, command);
					break;
				default:
					break;
				}
				break;
			}
		}
		return commands;
	}

	private static Command commandCreator(String text) {
		Command command = null;
		try {
			Class<?> c = Class.forName(text);
			command = (Command) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			log.error(e);
			throw new ControllerException("fail in commandCreator(String text)", e);
		}
		return command;
	}
}
