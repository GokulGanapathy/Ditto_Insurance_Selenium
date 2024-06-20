package TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjects.InsuranceSelectPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public void instantiateBrowser() {
		String browser = "Chrome";
		if(browser.contains("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.contains("Firefox")) {
			driver = new FirefoxDriver();	
		}
		else if(browser.contains("Edge")) {
			driver = new EdgeDriver();
		}
	}
	
	public String getScreenShot(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenShot = ts.getScreenshotAs(OutputType.FILE);
		
		String destPath = System.getProperty("user.dir")+"//ScreenShots//"+testCaseName+".png";
		File dest = new File(destPath);
		FileUtils.copyFile(screenShot, dest);
		
		return destPath;
	}
	
	public InsuranceSelectPage invokeBrowser() {
		instantiateBrowser();
		driver.get("https://app.joinditto.in/fq");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		InsuranceSelectPage insuranceSelect = new InsuranceSelectPage(driver);
		return insuranceSelect;
	}
	
	@AfterMethod
	public void CloseBorwser() {
		driver.quit();
	}

}
