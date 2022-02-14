package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;

//import Listener.MyExtentListener;


public class TestUtil {

	public static String TESTDATA_SHEET_NAME = ".\\src\\test\\resources\\properties\\Tenant information.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static WebDriver driver;
	static WebDriverWait wait;
	public long ETO = 5;
	
	public static JavascriptExecutor jsExecutor;
	public static Actions action;
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public TestUtil(WebDriver driver, long ETO)
	{
		TestUtil.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
	
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	
	

	public static void sleep(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* Verify the Element is Click-able or Not */
	public static boolean isElementClickable(WebElement element, String elementName)  {
//		try {
			logger.info("---------Method is Element clickable  ---------");
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
//		}
//		catch (Exception e)
//		{
//			MyExtentListener.logger
//					.fail(MarkupHelper.createLabel(
//							"Verify user is able to mouse hover on " + "\'" + elementName + "\'"
//									+ " ||  User is not able to mouse hover on " + "\'" + elementName + "\'",
//							ExtentColor.RED));
//			try {
//				MyExtentListener.logger.addScreenCaptureFromPath(capture(driver));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			Assert.fail("Unable to to mouse hover on " + elementName);
//		}
//		return false;
	}
	
	/* Click on the Element */
	public static  void clickOnElement(WebElement element, String elementName) 
	{
		try
		{
//			if (isElementClickable(element, elementName))
//			{
//				logger.info("-----------Clicking on the Element----------");
				 element.click();
//			} 
		}
		catch (Exception e)
		{
//			MyExtentListener.logger.fail(MarkupHelper
//					.createLabel("Unable to Click on " + "\'" + elementName + "\'", ExtentColor.RED));
//			try {
//				MyExtentListener.logger.addScreenCaptureFromPath(capture(driver));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} // exception
			Assert.fail("Unable to Click on " + "\'" + elementName + "\'");
		}
	}

	/* To Enter the Text to the Text filed */
	public static void typeText(WebElement element, String value, String elementName) 
	{
		
		try
		{
//			waitTillPageLoad(driver, 2);
			try {
//				waitForElement(element, driver, elementName, 1);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Enter the value into" + elementName);
			element.sendKeys(value);
			logger.info("User is able to type " + value + " into " + elementName);
		} 
		catch (AssertionError error)
		{
			logger.info(" User is not able to type " + value + " into " + elementName);
			// MyExtentListners.test.addScreenCaptureFromPath(capture(driver, element));
			Assert.fail("Unable to type on " + elementName);
		}
		catch (Exception e)
		{
			logger.info(" User is not able to type " + value + "into " + elementName);
			// MyExtentListners.test.addScreenCaptureFromPath(capture(driver, element));
			Assert.fail("Unable to type in " + elementName);
		}
	}
	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		String[][] data = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}

		return data;

	}

}
