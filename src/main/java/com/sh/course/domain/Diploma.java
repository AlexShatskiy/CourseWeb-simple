package com.sh.course.domain;

import com.sh.course.domain.parameter.DiplomaStatus;

public class Diploma {

	private int userId;
	private int courseId;
	private int lecturerId;
	
	private String comment;
	private int rating;
	private DiplomaStatus statys;

	public Diploma() {
		super();
	}

	public Diploma(int userId, int courseId, int lecturerId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.lecturerId = lecturerId;
	}

	public Diploma(int userId, int courseId, int lecturerId, String comment, int rating) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.lecturerId = lecturerId;
		this.comment = comment;
		this.rating = rating;
	}

	public Diploma(int userId, int courseId, int lecturerId, String comment, int rating, DiplomaStatus statys) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.lecturerId = lecturerId;
		this.comment = comment;
		this.rating = rating;
		this.statys = statys;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(int lecturerId) {
		this.lecturerId = lecturerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public DiplomaStatus getStatys() {
		return statys;
	}

	public void setStatys(DiplomaStatus statys) {
		this.statys = statys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + courseId;
		result = prime * result + lecturerId;
		result = prime * result + rating;
		result = prime * result + ((statys == null) ? 0 : statys.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diploma other = (Diploma) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (courseId != other.courseId)
			return false;
		if (lecturerId != other.lecturerId)
			return false;
		if (rating != other.rating)
			return false;
		if (statys != other.statys)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Diploma [userId=" + userId + ", courseId=" + courseId + ", lecturerId=" + lecturerId + ", comment="
				+ comment + ", rating=" + rating + ", statys=" + statys + "]";
	}
}
