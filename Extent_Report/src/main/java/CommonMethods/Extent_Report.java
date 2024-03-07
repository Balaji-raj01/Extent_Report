package CommonMethods;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class Extent_Report {
	WebDriver driver = null;
	static ExtentSparkReporter spark;
	public static ExtentTest test;
	static ExtentReports extent;

	public static ExtentReports reportsetup() {

		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Extent/Report.html");

		String css = "@media only screen and (min-width: 992px) {" + ".test-wrapper .test-list .test-list-wrapper {"
				+ "height: calc(100vh - 106px);" + "}" + ".test-wrapper .test-list {" + "width: 25%;" + "}"
				+ ".test-wrapper .test-content {" + "width: 65%;" + "}" + "}";

		spark.config().setCss(css);

//		String cssLogo = ".logo { background-image: url('https://imgur.com/9JIerjE'); }";
//
//		spark.config().setCss(cssLogo);
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation");
		spark.config().setReportName("Test report by TrackDfect");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("JAVA", System.getProperty("java.version"));
		extent.setSystemInfo("APP URL", "https://pr-2534.d1crrk270ro90j.amplifyapp.com/");
		extent.setSystemInfo("Bowser", "Chrome");
		spark.viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY, ViewName.AUTHOR });

		return extent;
	}

	public static void teardown(ITestResult result, WebDriver driver, String TestCaseName, String AuthorName,
			String Category, ExtentTest test) throws Throwable {
		// test=extent.createTest(TestCaseName).assignAuthor(AuthorName).assignCategory(Category);
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "<b><i>Test case Failed is " + result.getName() + "</i><b>");
			test.log(Status.FAIL, "Test case Failed is " + result.getThrowable());
			String path = takescreenshot1(driver, result.getName());
			test.addScreenCaptureFromPath(path);
			

		} else if (result.getStatus() == ITestResult.SKIP) {

			test.log(Status.SKIP, "<b><i>Test case Skipped is " + result.getName() + "</i><b>");
			String path = takescreenshot1(driver, result.getName());
			test.addScreenCaptureFromPath(path);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "<b><i>Test case Passed is " + result.getName() + "</i><b>");
			String path = takescreenshot1(driver, result.getName());
			test.addScreenCaptureFromPath(path);
			// test.addScreenCaptureFromBase64String("Passed", result.getName());

		}

	}

	public ExtentTest TestLevel(String TestCaseName, String AuthorName, String Category) {
		test = extent.createTest(TestCaseName).assignAuthor(AuthorName).assignCategory(Category);

		System.out.println(this.test);
		return test;
	}

	public void Aftertest() {
		extent.flush();
	}

	public void Extent_pass(WebDriver driver, String value, boolean flag, String TestcaseName, String Name,
			String TestCaseName, String AuthorName, String Category) {
		if (flag == true) {
			test = TestLevel(TestCaseName, AuthorName, Category);
			// test=extent.createTest(TestCaseName).assignAuthor(AuthorName).assignCategory(Category);
			// test=TestLevel(TestCaseName,AuthorName,Category);
			// test=extent.createTest(TestcaseName).pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,Name)).build());
			// test=extent.createTest("TC_01").pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,"1")).build());
			// test.pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,Name),TestcaseName).build());
			test.pass(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver, Name), TestcaseName)
					.build());
			// test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(TestcaseName,
			// Name).build());

		}
	}

	public void Extent_Fail(WebDriver driver, String value, boolean flag, String TestcaseName, String Name) {
		if (flag == true) {

			// test.log(Status.FAIL,
			// MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver,Name),
			// TestcaseName).build());
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(takescreenshot1(driver, Name)).build());
		}
	}

	public static String takescreenshot1(WebDriver driver, String screenshot_path) {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Screenshots1" + screenshot_path + ".png");
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
		}
		return dest.getAbsolutePath();
	}

}
