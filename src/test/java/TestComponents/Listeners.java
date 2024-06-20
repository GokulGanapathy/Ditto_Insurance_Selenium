package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporting.CreateExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, MarkupHelper.createLabel(
				"The Test "+result.getMethod().getMethodName()+" got Passed", ExtentColor.GREEN));
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, MarkupHelper.createLabel(
				"The Test "+result.getMethod().getMethodName()+" is Skipped", ExtentColor.YELLOW));
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String filePath="";
		try {
			filePath = getScreenShot(driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
