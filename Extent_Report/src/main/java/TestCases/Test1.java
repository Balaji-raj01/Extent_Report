package TestCases;

import org.openqa.selenium.WebDriver;

import CommonMethods.*;

public class Test1 extends Keywords implements Locators {

	public void TC_1(WebDriver driver) {
		
		driver.navigate().to("https://www.google.com/");
		driver.manage().window().maximize();
		click(driver,Search);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendkeys(driver, Search, "Wikipedia");
		enter(driver);
	

	}
	
	
	public void TC_2(WebDriver driver){
		
		driver.navigate().to("https://www.google.com/");
		driver.manage().window().maximize();
		click(driver,Search);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendkeys(driver, Search, "Snaptrude");
		enter(driver);
}
}