package com.controller.selenium.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum SeleniumControllerErrorCode {
	
	GENERIC(100, "An error occurred while executing the Selenium test case. Please review log files for further details."),
	NON_UNIQUE(200, "An error occurred in which a unique value was expected but more than once istance occurred."),
	NOT_FOUND(300, "An error occurred in which an element was not found given the lookup paramters and it was expected."),
	BAD_FORMAT(310, "An error occurred in which an element could not be found given that lookup paramters provided were in an invalid format.");
	
	private String message;
	private int code;
	
	private SeleniumControllerErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static String getMessage(SeleniumControllerErrorCode code) {
		return Arrays.asList(values()).stream().filter(e -> (e.code == code.code)).collect(Collectors.toList()).get(0).message;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.code).append("::").append(this.message);
		return sb.toString();
	}
}
