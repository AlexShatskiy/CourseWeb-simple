package com.sh.course.controller.command.provider;

import java.util.HashMap;
import java.util.Map;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.impl.AddCourse;
import com.sh.course.controller.command.impl.CheckIn;
import com.sh.course.controller.command.impl.PasswordRecovery;
import com.sh.course.controller.command.impl.SignIn;
import com.sh.course.controller.command.impl.SignOut;
import com.sh.course.controller.command.impl.get.GetAvailableCourse;
import com.sh.course.controller.command.impl.page.AddCoursePage;
import com.sh.course.controller.command.impl.page.PasswordRecoveryPage;
import com.sh.course.controller.command.impl.page.ProfilePage;
import com.sh.course.controller.command.impl.page.RegistrationPage;
import com.sh.course.controller.command.impl.page.SignInPage;
import com.sh.course.controller.command.parameter.CommandName;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.CHECK_IN, new CheckIn());
		commands.put(CommandName.PASSWORD_RECOVERY, new PasswordRecovery());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.ADD_COURSE, new AddCourse());
		
		//to page
		commands.put(CommandName.PASSWORD_RECOVERY_PAGE, new PasswordRecoveryPage());
		commands.put(CommandName.PROFILE_PAGE, new ProfilePage());
		commands.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
		commands.put(CommandName.SIGN_IN_PAGE, new SignInPage());
		commands.put(CommandName.ADD_COURSE_PAGE, new AddCoursePage());
		
		//get
		commands.put(CommandName.GET_AVAILABLE_COURSE, new GetAvailableCourse());
		
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
