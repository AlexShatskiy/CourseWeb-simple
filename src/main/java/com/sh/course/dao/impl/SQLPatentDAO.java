package com.sh.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sh.course.dao.PatentDAO;
import com.sh.course.dao.exception.ConnectionPoolException;
import com.sh.course.domain.Patent;


public class SQLPatentDAO implements PatentDAO {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public Patent addPatent(Patent patent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patent> getPatent(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
