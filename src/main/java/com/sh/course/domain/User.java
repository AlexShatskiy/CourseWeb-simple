package com.sh.course.domain;

import com.sh.course.domain.parameter.Role;

public class User {

	private Integer userId;
	private String email;
	private String nickname;
	private Role role;
	
	public User() {
		super();
	}

	public User(Integer userId, String email, String nickname, Role role) {
		super();
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

	
	

}
