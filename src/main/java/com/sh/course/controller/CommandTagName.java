package com.sh.course.controller;

public enum CommandTagName {

	COMMANDS, COMMAND, NAME, REALIZATION;

	public static CommandTagName getElementTagName(String element) {
		switch (element) {
		case "commands":
			return COMMANDS;
		case "command":
			return COMMAND;
		case "name":
			return NAME;
		case "realization":
			return REALIZATION;
		default:    throw new RuntimeException(element);
		}
	}
}
