package com.sh.course.dao;

import java.util.List;

import com.sh.course.domain.Patent;

public interface PatentDAO {
	Patent addPatent(Patent patent);
	List<Patent> getPatent(int userId);
}
