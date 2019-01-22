package com.controller.selenium.exception;

public class SeleniumControllerException extends Throwable {

	/**
	 * Auto-generated serial version ID value.
	 */
	private static final long serialVersionUID = -4462037729789570094L;
	
	public SeleniumControllerException() {
		super(SeleniumControllerErrorCode.GENERIC.toString());
	}
	
	public SeleniumControllerException(String message) {
		super(message);
	}
	
	public SeleniumControllerException(SeleniumControllerErrorCode code) {
		super(SeleniumControllerErrorCode.getMessage(code));
	}
	
	public SeleniumControllerException(SeleniumControllerErrorCode code, String message) {
		super(buildCustomMessage(code, message));
	}
	
	/**
	 * Used to generate the custom message that populates the exception for the developer to review 
	 * @param errorCode the Selenium Controller error code selected
	 * @param customMsg the custom message to append, usually will indicate the exact error to look for when reviewing by the developed
	 * @return the merging of the two messages into the one custom message
	 */
	private static String buildCustomMessage(SeleniumControllerErrorCode errorCode, String customMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append(SeleniumControllerErrorCode.getMessage(errorCode))
			.append("::")
			.append(customMsg);
		return sb.toString();
	}
}

