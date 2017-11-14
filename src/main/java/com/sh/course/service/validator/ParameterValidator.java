package com.sh.course.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shatskiy Alex
 * @version 1.0
 */
public final class ParameterValidator {
	
	private ParameterValidator() {
		super();
	}

	private static String EMAIL_REGEXP = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
	private static String ID_REGEXP = "\\d+";
	private static String RATING_REGEXP = "[1-9]|10";

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
	
	public static boolean isTextValid(String text) {
		if (text == null) {
			return false;
		} else if (text.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean isIdValid(String id) {
		if (id == null) {
			return false;
		} else if (id.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile(ID_REGEXP);
		Matcher matcher = pattern.matcher(id);

		return matcher.matches();
	}
	
	public static boolean isRatingValid(String rating) {
		if (rating == null) {
			return false;
		} else if (rating.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile(RATING_REGEXP);
		Matcher matcher = pattern.matcher(rating);

		return matcher.matches();
	}
}
