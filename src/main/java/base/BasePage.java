package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public static Properties Config;
	public static FileInputStream fis;

	public BasePage() {

		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config = new Properties();
			Config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@BeforeSuite
	public void setUp() {
		
		String browserName = Config.getProperty("browser");
		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(Config.getProperty("testurl"));
		System.out.println("Login Completed");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(Config.getProperty("implicitWait")), TimeUnit.SECONDS);
		
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();

	}

}

