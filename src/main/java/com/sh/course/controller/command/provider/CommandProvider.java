package com.sh.course.controller.command.provider;

import java.util.HashMap;
import java.util.Map;

import com.sh.course.controller.command.Command;
import com.sh.course.controller.command.impl.CheckIn;
import com.sh.course.controller.command.impl.PasswordRecovery;
import com.sh.course.controller.command.impl.SignIn;
import com.sh.course.controller.command.impl.SignOut;
import com.sh.course.controller.command.impl.get.GetAllCoursesLecturer;
import com.sh.course.controller.command.impl.get.GetAllLecturersCourse;
import com.sh.course.controller.command.impl.get.GetAvailableCourses;
import com.sh.course.controller.command.impl.get.GetStudentsFinish;
import com.sh.course.controller.command.impl.get.GetStudentsStudy;
import com.sh.course.controller.command.impl.get.SearcAvailableCourses;
import com.sh.course.controller.command.impl.page.AddCoursePage;
import com.sh.course.controller.command.impl.page.PasswordRecoveryPage;
import com.sh.course.controller.command.impl.page.ProfilePage;
import com.sh.course.controller.command.impl.page.RateStudentPage;
import com.sh.course.controller.command.impl.page.RegistrationPage;
import com.sh.course.controller.command.impl.page.SignInPage;
import com.sh.course.controller.command.impl.post.AddCourse;
import com.sh.course.controller.command.impl.post.AddLecturerCourse;
import com.sh.course.controller.command.impl.post.DeleteLecturerCourse;
import com.sh.course.controller.command.impl.post.EnrollForCourse;
import com.sh.course.controller.command.impl.post.RateStudent;
import com.sh.course.controller.command.impl.result.BedAddCourse;
import com.sh.course.controller.command.impl.result.BedAddHasTitleCourse;
import com.sh.course.controller.command.impl.result.BedAddInvalidCourse;
import com.sh.course.controller.command.impl.result.BedAddLecturerCourse;
import com.sh.course.controller.command.impl.result.BedDeleteLecturerCourse;
import com.sh.course.controller.command.impl.result.BedEnrollForCourse;
import com.sh.course.controller.command.impl.result.BedEnrollForCourseHas;
import com.sh.course.controller.command.impl.result.GoodAddCourse;
import com.sh.course.controller.command.impl.result.GoodAddLecturerCourse;
import com.sh.course.controller.command.impl.result.GoodDeleteLecturerCourse;
import com.sh.course.controller.command.impl.result.GoodEnrollForCourse;
import com.sh.course.controller.command.parameter.CommandName;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.CHECK_IN, new CheckIn());
		commands.put(CommandName.PASSWORD_RECOVERY, new PasswordRecovery());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.ADD_COURSE, new AddCourse());
		
		
		commands.put(CommandName.PASSWORD_RECOVERY_PAGE, new PasswordRecoveryPage());
		commands.put(CommandName.PROFILE_PAGE, new ProfilePage());
		commands.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
		commands.put(CommandName.SIGN_IN_PAGE, new SignInPage());
		commands.put(CommandName.ADD_COURSE_PAGE, new AddCoursePage());
		
		
		commands.put(CommandName.SEARCH_AVAILABLE_COURSE, new SearcAvailableCourses());
		commands.put(CommandName.GET_AVAILABLE_COURSE, new GetAvailableCourses());
		commands.put(CommandName.GET_ALL_COURSE_LECTURER, new GetAllCoursesLecturer());
		
		
		commands.put(CommandName.GOOD_ADD_COURSE, new GoodAddCourse());
		commands.put(CommandName.BED_ADD_COURSE, new BedAddCourse());
		commands.put(CommandName.BED_ADD_HAS_TITLE_COURSE, new BedAddHasTitleCourse());
		commands.put(CommandName.BED_ADD_INVALID_COURSE, new BedAddInvalidCourse());
		
		commands.put(CommandName.ADD_LECTURER_COURSE, new AddLecturerCourse());
		commands.put(CommandName.DELETE_LECTURER_COURSE, new DeleteLecturerCourse());
		
		commands.put(CommandName.GOOD_ADD_LECTURER_COURSE, new GoodAddLecturerCourse());
		commands.put(CommandName.BED_ADD_LECTURER_COURSE, new BedAddLecturerCourse());
		commands.put(CommandName.GOOD_DELETE_LECTURER_COURSE, new GoodDeleteLecturerCourse());
		commands.put(CommandName.BED_DELETE_LECTURER_COURSE, new BedDeleteLecturerCourse());
		
		commands.put(CommandName.GET_ALL_LECTURER_COURSE, new GetAllLecturersCourse());
		
		commands.put(CommandName.ENROLL_FOR_COURSE, new EnrollForCourse());
		commands.put(CommandName.GOOD_ENROLL_FOR_COURSE, new GoodEnrollForCourse());
		commands.put(CommandName.BED_ENROLL_FOR_COURSE, new BedEnrollForCourse());
		commands.put(CommandName.BED_ENROLL_FOR_COURSE_HAS, new BedEnrollForCourseHas());
		
		commands.put(CommandName.GET_STUDENT_STUDY, new GetStudentsStudy());
		commands.put(CommandName.GET_STUDENT_FINISH, new GetStudentsFinish());
		
		commands.put(CommandName.RATE_STUDENT_PAGE, new RateStudentPage());
		commands.put(CommandName.RATE_STUDENT, new RateStudent());
	}

	public Command getCommand(String commandName) {
		commandName = commandName.toUpperCase();
		return commands.get(CommandName.valueOf(commandName));
	}
}
