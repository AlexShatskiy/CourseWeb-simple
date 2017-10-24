package com.sh.course.service;

import java.util.List;

import com.sh.course.domain.Diploma;

public interface PatentService {
	Diploma addPatent(int userId, int courseId, String comment, int rating);
	List<Diploma> getPatent(int userId);
}
