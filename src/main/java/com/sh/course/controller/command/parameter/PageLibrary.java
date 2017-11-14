package com.sh.course.controller.command.parameter;

public final class PageLibrary {
	
	private PageLibrary(){}
	
	public static final String INDEX = "index.jsp";
	public static final String SIGN_IN_PAGE = "WEB-INF/jsp/signin.jsp";
	public static final String REGISTRATION_PAGE = "WEB-INF/jsp/signup.jsp";
	public static final String PASSWORD_RECOVERY = "WEB-INF/jsp/passwordRecovery.jsp";
	
	public static final String USER_PROFILE = "WEB-INF/jsp/userProfile.jsp";
	public static final String LECTURER_PROFILE = "WEB-INF/jsp/lecturerProfile.jsp";
	public static final String ADD_COURSE_PAGE = "WEB-INF/jsp/addingCourse.jsp";
	public static final String RATE_STUDENT_PAGE = "WEB-INF/jsp/rateStudent.jsp";
	
	public static final String URL_GOOD_ADD_COURSE = "controller?command=GOOD_ADD_COURSE";
	public static final String URL_BED_ADD_COURSE = "controller?command=BED_ADD_COURSE";
	public static final String URL_BED_ADD_HAS_TITLE_COURSE = "controller?command=BED_ADD_HAS_TITLE_COURSE";
	public static final String URL_BED_ADD_INVALID_COURSE = "controller?command=BED_ADD_INVALID_COURSE";
	
	public static final String URL_GOOD_ADD_LECTURER_COURSE = "controller?command=GOOD_ADD_LECTURER_COURSE";
	public static final String URL_BED_ADD_LECTURER_COURSE = "controller?command=BED_ADD_LECTURER_COURSE";
	
	public static final String URL_GOOD_DELETE_LECTURER_COURSE = "controller?command=GOOD_DELETE_LECTURER_COURSE";
	public static final String URL_BED_DELETE_LECTURER_COURSE = "controller?command=BED_DELETE_LECTURER_COURSE";
	
	public static final String URL_USER_PROFILE = "controller?command=PROFILE_PAGE";
	
	public static final String URL_GOOD_ENROLL_FOR_COURSE = "controller?command=GOOD_ENROLL_FOR_COURSE";
	public static final String URL_BED_ENROLL_FOR_COURSE = "controller?command=BED_ENROLL_FOR_COURSE";
	public static final String URL_BED_ENROLL_FOR_COURSE_HAS = "controller?command=BED_ENROLL_FOR_COURSE_HAS";
	
	public static final String URL_GOOD_RATING = "controller?command=GOOD_RATING";
	public static final String URL_BED_RATING = "controller?command=BED_RATING";
}
