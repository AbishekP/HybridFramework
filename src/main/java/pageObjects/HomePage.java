package pageObjects;

import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private Logger log = LogManager.getLogger(HomePage.class.getName());
	public WebDriver driver;
	private By searchBox = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-button");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement searchBox() {
		log.debug("Search Box element is accessed");
		return driver.findElement(searchBox);
	}

	public WebElement searchButton() {
		log.debug("Search Button element is accessed");
		return driver.findElement(searchButton);
	}
}
