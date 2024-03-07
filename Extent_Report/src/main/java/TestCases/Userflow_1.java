package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import CommonMethods.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Userflow_1 extends Extent_Report implements Locators{

	Test1 s =new Test1();
	ExtentTest test;


	Extent_Report r=new Extent_Report();
	WebDriver driver=null;


	ExtentReports extent;


	@BeforeTest
	public void extent_Report() {
			WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver=new ChromeDriver(options);
		Dimension size = new Dimension(1200, 800);
        driver.manage().window().setSize(size);
		
		extent=Extent_Report.reportsetup();

	}

	

	@AfterTest
	public void aftertest() {
		r.Aftertest();

	}

	@Test(priority=1)
	public void TC_1() throws InterruptedException {
		test=extent.createTest("TC_01").assignAuthor("Balaji").assignCategory("Sanity");
		Keywords.ActionsTest(test);
		s.TC_1(driver);
		//driver.quit();

	}

	@Test(priority=2)
	public void TC_2() {
		test=extent.createTest("TC_02").assignAuthor("Balaji").assignCategory("Sanity");
		Keywords.ActionsTest(test);
		s.TC_2(driver);
		//driver.quit();
	}

	@AfterMethod
	public void Teardown(ITestResult result) throws Throwable {
		Extent_Report.teardown(result,driver,"TC_01", "Balaji", "Sanity",test);

	}


}




