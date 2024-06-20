package TestComponents;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporting {
	
	public static ExtentReports CreateExtentReport() {
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//index.html");
		sparkReporter.config().setReportName("DittoInsuranceReport");
		sparkReporter.config().setDocumentTitle("DittoInsuranecReport");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester", "Gokul");
		extent.setSystemInfo("TestingDate", new Date().toString());
		return extent;
	}

}
