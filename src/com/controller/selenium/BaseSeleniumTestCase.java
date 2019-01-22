package com.controller.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.controller.selenium.exception.SeleniumControllerErrorCode;
import com.controller.selenium.exception.SeleniumControllerException;
import com.controller.selenium.util.ByType;

import lombok.Data;

@Data
public class BaseSeleniumTestCase {
	/**
	 * The base driver to use for executing against the browser
	 */
	private WebDriver driver;
	
	/**
	 * Constructor that requires a driver class.
	 * @param driver the driver instance, can be Firefox, IE, Chrome, etc
	 */
	public BaseSeleniumTestCase(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Method used to tell the classes driver what site to navigate to.
	 * @param address the website address to navigate to
	 */
	protected void navigateToAddress(String address) {
		driver.get(address);
	}
	
	/**
	 * Method used to obtain the specific element on the site based upon its unique ID value
	 * @param idValue the unique value associated with the element on the site
	 * @return the {@link WebElement} that contains the expected ID value
	 * @throws SeleniumControllerException throws this exception if more than one result was found on the website for the ID value or 
	 * if the element is not found
	 */
	protected WebElement getElementById(String idValue) throws SeleniumControllerException{
		List<WebElement> results = getElementsBy(ByType.ID, idValue);
		if(results.size() > 0) {
			return results.get(0);
		}
		else if(results.size() > 1) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NON_UNIQUE, 
					"Expected a unique ID value of '" + idValue + "' but more than one result was returned.");
		}
		throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
				"Unable to find a matching element with the ID value of '" + idValue + "'.");
		
	}
	
	/**
	 * Method used to obtain a listing of elements that match the name attribute of a HTML tag. It must be a valid name value that passes the 
	 * standard RegEx pattern of [a-zA-Z0-9_:-].
	 * @param nameValue the value of the name attribute to find elements of
	 * @return a listing of {@link WebElement} objects that have the requested name value
	 * @throws SeleniumControllerException throws this exception if the name value is not valid within the RegEx for a HTML name attribute or 
	 * if the element is not found
	 */
	protected List<WebElement> getElementsByName(String nameValue) throws SeleniumControllerException{
		if(!checkNameValidity(nameValue)) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.BAD_FORMAT,
					"The name value passed in ('" + nameValue + "') is not a valid HTML name value and will result in no elements returned.");
		}
		
		List<WebElement> results = getElementsBy(ByType.NAME, nameValue);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the name value of '" + nameValue + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match the class attribute of a HTML tag.
	 * @param className the value of the class attribute. This must be from start to X meaning it can not find just X
	 * @return a listing of {@link WebElement} objects that have the requested class value
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByClassName(String className) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.CLASS_NAME, className);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the class name value of '" + className + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match the CSS selector value passed in. 
	 * @param cssSelector the CSS selection string
	 * @return a listing of {@link WebElement} objects that match the requested CSS selector value
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByCss(String cssSelector) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.CSS, cssSelector);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the css selector of '" + cssSelector + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match in full the passed in link text. So if passed in foo and the link was 'foo bar'
	 * it would not locate or return the link element.
	 * @param linkText the entire string of visible link text to search on
	 * @return a listing of {@link WebElement} objects that match the requested link text
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByLinkText(String linkText) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.LINK_TEXT, linkText);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the link text of '" + linkText + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match in part the passed in link text. So if passed in foo and the link was 'foo bar'
	 * it would locate and return the link element.
	 * @param linkText the section of the visible link text to search on
	 * @return a listing of {@link WebElement} objects that match the requested partial link text
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByPartialLinkText(String linkText) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.PARTIAL_LINK_TEXT, linkText);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the link text partially of '" + linkText + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match the name of the HTML tag passed in.
	 * @param tagName the HTML tag name to get all elements for
	 * @return a listing of {@link WebElement} objects that match the requested tag name
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByTagName(String tagName) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.TAG_NAME, tagName);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the tag name of '" + tagName + "'.");
		}
		
		return results;
	}
	
	/**
	 * Method used to obtain a listing of elements that match the xpath search criteria.
	 * @param tagName the xpath search criteria to get all elements for
	 * @return a listing of {@link WebElement} objects that match the requested xpath search criteria
	 * @throws SeleniumControllerException throws this exception if the element is not found
	 */
	protected List<WebElement> getElementsByXPath(String xpath) throws SeleniumControllerException {
		List<WebElement> results = getElementsBy(ByType.XPATH, xpath);
		if(results.size() == 0) {
			throw new SeleniumControllerException(SeleniumControllerErrorCode.NOT_FOUND, 
					"Unable to find a matching element with the xpath of '" + xpath + "'.");
		}
		
		return results;
	}
	
	/**
	 * Used to determine the proper calls to make to the Selenium driver for obtaining the required {@link WebElement}
	 * @param type the {@link ByType} we are attempting to utilize to query the DOM
	 * @param value the value associated to actually perform the query
	 * @return the listing of all elements that match the criteria, or an empty list if not applicable
	 */
	private List<WebElement> getElementsBy(ByType type, String value) {
		switch(type) {
		case ID:
			return driver.findElements(By.id(value));
		case NAME:
			return driver.findElements(By.name(value));
		case CLASS_NAME:
			return driver.findElements(By.className(value));
		case CSS:
			return driver.findElements(By.cssSelector(value));
		case LINK_TEXT:
			return driver.findElements(By.linkText(value));
		case PARTIAL_LINK_TEXT:
			return driver.findElements(By.partialLinkText(value));
		case TAG_NAME:
			return driver.findElements(By.tagName(value));
		case XPATH:
			return driver.findElements(By.xpath(value));
		default:
			break;
		}
		return new ArrayList<WebElement>();
	}
	
	private boolean checkNameValidity(String input) {
		return input.matches("[a-zA-Z0-9_:-]");
	}
}
