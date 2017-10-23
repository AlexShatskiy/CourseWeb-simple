package com.sh.course.domain;

import com.sh.course.domain.parameter.CourseStatus;

public class Course {
	
	private Integer courseId;
	private String title;
	private String content;
	private CourseStatus status;
	private Integer userId;
	
	public Course() {
		super();
	}

	public Course(Integer courseId, String title, String content, CourseStatus status, Integer userId) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.userId = userId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	
	


	

}
