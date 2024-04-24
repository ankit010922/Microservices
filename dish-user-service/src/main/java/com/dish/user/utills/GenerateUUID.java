package com.dish.user.utills;

import java.util.UUID;

import lombok.Getter;

@Getter
public class GenerateUUID {

	private GenerateUUID() {

	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

}
