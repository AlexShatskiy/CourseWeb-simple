package com.sh.course.domain;

public class Patent {

	private int userId;
	private int courseId;
	private String comment;
	private int rating;

	public Patent() {
		super();
	}

	public Patent(int userId, int courseId, String comment, int rating) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.comment = comment;
		this.rating = rating;
	}

}
