package com.sh.course.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParameterValidator {
	
	private static String EMAIL_REGEXP = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

	public static boolean isEmailValid(String email) {
		if (email == null) {
			return false;
		} else if (email.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static boolean isPasswordValid(String password) {
		if (password == null) {
			return false;
		} else if (password.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isNicknameValid(String nickname) {
		if (nickname == null) {
			return false;
		} else if (nickname.isEmpty()) {
			return false;
		}
		return true;
	}

}
