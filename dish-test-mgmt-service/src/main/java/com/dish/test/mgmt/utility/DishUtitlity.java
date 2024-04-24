package com.dish.test.mgmt.utility;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class DishUtitlity {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

	private static char randomSpecialChar() {
		SecureRandom random = new SecureRandom();
		return SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length()));
	}

	private static String generateRandomPassword() {
		SecureRandom random = new SecureRandom();
		// At least one uppercase letter
		char uppercaseChar = UPPERCASE_CHARS.charAt(random.nextInt(UPPERCASE_CHARS.length()));
		// At least one lowercase letter
		char lowercaseChar = LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length()));
		// At least six random characters
		StringBuilder randomChars = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			char randomChar = LOWERCASE_CHARS.charAt(random.nextInt(LOWERCASE_CHARS.length()));
			randomChars.append(randomChar);
		}
		// Combine the characters to form the password
		String password = uppercaseChar + "" + lowercaseChar + randomChars + randomSpecialChar();

		return password;
	}

	public static List<String> samplePasswords() {
		List<String> passwordSamples = new ArrayList<String>();
		for (int iterator = 0; iterator < 5; iterator++) {
			passwordSamples.add(generateRandomPassword());
		}
		return passwordSamples;
	}

	public String md5Encryptor(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}


	/**
	 * Generated the Unique identifier
	 * 
	 * @return UUID
	 */
	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
