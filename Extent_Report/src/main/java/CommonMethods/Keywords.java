package CommonMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class Keywords extends Extent_Report {
	static ExtentTest test;
	
	public static void ActionsTest(ExtentTest extentTest){
		test=extentTest;
		
	}
	
	public static String[] splitXpath(String path) {
		String[] a = path.split(">");
		return a;
	}
	
	public static String takescreenshot1(WebDriver driver, String screenshot_path) {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshots" + screenshot_path + ".png");
		try {
			FileUtils.copyFile(scr, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return dest.getAbsolutePath();
	}
	
	public void click(WebDriver driver, String Xpath) {		
		String[] values = splitXpath(Xpath);
		try {
		
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(values[1])));
			WebElement element = driver.findElement(By.xpath(values[1]));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);
			executor.executeScript("arguments[0].click();", element);			
	//	Extent_pass(driver,"click", true,TestcaseName,values[0],"TC_1", "Balaji", "Sanity");
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,values[0]),values[0]).build());
			
		} catch (Exception e) {
			e.printStackTrace();
		//s	Extent_Fail(driver,values[0]);
	//	Extent_Fail(driver,"click", true,TestcaseName,values[0]);	
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,values[0]),values[0]).build());
			Assert.fail();
			
		}
	}
public void sendkeys(WebDriver driver, String xpaths, String keysToSend) {
	String[] values = splitXpath(xpaths);
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebDriverWait wait1 = new WebDriverWait(driver, 20);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(values[1])));
				WebElement webElement = driver.findElement(By.xpath(values[1]));
				webElement.sendKeys(keysToSend);	
				//Extent_pass(driver,"sendkeys", true,TestcaseName,values[0],"TC_1", "Balaji", "Sanity");	
				test.pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,keysToSend),values[0]).build());
			} catch (Exception e) {
				
		//	Extent_Fail(driver,"click", true,TestcaseName,values[0]);	
				test.fail(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,keysToSend),values[0]).build());
				Assert.fail();
			}

		}
	
public void enter(WebDriver driver) {
	try {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.ENTER).build().perform();
	} catch (Exception e) {
		Assert.fail();
	}
}
	
	}
