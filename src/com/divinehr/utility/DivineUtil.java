package com.divinehr.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extra utility class that helps in the flow of this application
 */
public class DivineUtil {

	/**
	 * @param It takes email as String validate the email using regex
	 * @return If the given email is a valid email, return true else false
	 */
	public static boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	/**
	 * 
	 * @param It takes time in milliseconds and make the current thread sleep
	 *           accordingly
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException igone) {
		}
	}
}
