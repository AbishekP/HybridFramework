package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {

	public WebDriver driver;
	public Properties prop;
	private static Logger log = LogManager.getLogger(Base.class.getName());

	public WebDriver base() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		//mvn test -Dbrowser=chrome
		//In Jenkins
		//General  -> This project is parameterized
		//In build actions -> mvn test -Dbrowser="$browser"
		//String browserName=System.getProperty("browser");  // Uncomment this line if you are sending parameter from Maven
		
		String browserName=prop.getProperty("browser");// comment this line if you are sending parameter from Maven


		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			if(browserName.contains("head")) {
				option.addArguments("headless");
			}

			driver = new ChromeDriver(option);
			log.info("Chrome Driver is initialised");

		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {

		} else if (prop.getProperty("browser").equalsIgnoreCase("IE")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
	
	public String getScreenshot(String methodName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+ File.separator +"reports" + File.separator +methodName+".png";
		FileUtils.copyFile(source, new File(destination));
		
		return destination;
	}
}
