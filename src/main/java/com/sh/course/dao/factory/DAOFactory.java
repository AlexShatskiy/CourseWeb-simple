package com.sh.course.dao.factory;

import com.sh.course.dao.CourseDAO;
import com.sh.course.dao.PatentDAO;
import com.sh.course.dao.UserDAO;
import com.sh.course.dao.impl.SQLCourseDAO;
import com.sh.course.dao.impl.SQLPatentDAO;
import com.sh.course.dao.impl.SQLUserDAO;

public class DAOFactory {
private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final CourseDAO sqlCourseImpl = new SQLCourseDAO();
	private final PatentDAO sqlPatentImpl = new SQLPatentDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	public UserDAO getUserDAO(){
		return sqlUserImpl;
	}
	
	public CourseDAO getCourseDAO(){
		return sqlCourseImpl;
	}
	
	public PatentDAO getPatentDAO(){
		return sqlPatentImpl;
	}
}
