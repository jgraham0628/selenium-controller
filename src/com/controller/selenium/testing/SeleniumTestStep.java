package com.controller.selenium.testing;

import lombok.Data;

@Data
public abstract class SeleniumTestStep {
	private boolean passed;
	
	public abstract boolean executeTestStep();
}
