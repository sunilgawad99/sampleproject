import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;

public class NewTest {
	ExtentSparkReporter report;
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;

	@Test
	public void skipTest() {

		driver.get("www.google.com");
		test = extent.createTest("skip test");
		test.log(Status.SKIP, "test be skiped");
		throw new SkipException("method skiped");
	}

	@Test
	public void failTest() {

		driver.get("www.google.com");
		test = extent.createTest("fail test");

		test.log(Status.FAIL, "test be failed");
		Assert.fail("test execation failed");
	}

	@Test
	public void successTest() {

		driver.get("https://www.google.com/");
		test = extent.createTest("success full");
		test.log(Status.PASS, "test be pass");
	}

	@BeforeClass
	public void beforeClass() {

		WebDriverManager.chromedriver().setup();
		report = new ExtentSparkReporter("./report/ex.html");
		report.config().setEncoding("utf-8");
		report.config().setDocumentTitle("Automation reports");
		report.config().setReportName("Automation Test result");
		report.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("organation", "sumasoft");
		extent.setSystemInfo("browser", "chrome");
		extent.attachReporter(report);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extent.flush();
	}
}
