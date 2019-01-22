package com.controller.selenium.testing;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.controller.selenium.SeleniumController;

public class SeleniumTestCase extends SeleniumController {
	List<Precondition> preconditions = new LinkedList<Precondition>();
	List<SeleniumTestStep> steps = new LinkedList<SeleniumTestStep>();
}
