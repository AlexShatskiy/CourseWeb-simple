package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Patent;

public interface PatentService {
	Patent addPatent(int userId, int courseId, String comment, int rating);
	List<Patent> getPatent(int userId);
}
