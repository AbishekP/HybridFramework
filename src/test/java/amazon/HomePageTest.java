package amazon;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.Base;

public class HomePageTest extends Base {
	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());
	public WebDriver driver;
	HomePage home;

	@BeforeTest
	public void Initdrivers() throws IOException {
		driver = base();
		log.debug("Browser is invoked");

	}

	@Test
	public void ClickSearch() throws IOException {
		driver.get(prop.getProperty("url"));
		log.debug("Click search method is running");
		home = new HomePage(driver);
		home.searchBox().sendKeys("Note 10");

	}

	@Test
	public void SearchButton() {
		log.debug("Search Button method is running");
		home.searchButton().click();
		Assert.assertTrue(home.searchButton().isDisplayed());
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
	}

}
