package com.sh.course.service.factory;

import com.sh.course.service.CourseService;
import com.sh.course.service.DiplomaService;
import com.sh.course.service.UserService;
import com.sh.course.service.impl.CourseServiceImpl;
import com.sh.course.service.impl.DiplomaServiceImpl;
import com.sh.course.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private CourseService courseService = new CourseServiceImpl();
	private DiplomaService diplomaService = new DiplomaServiceImpl();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public CourseService getCourseService(){
		return courseService;
	}
	
	public DiplomaService getDiplomaService(){
		return diplomaService;
	}
}
