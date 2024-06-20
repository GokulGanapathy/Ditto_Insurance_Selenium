package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int reTries=2;
	@Override
	public boolean retry(ITestResult result) {
		if(reTries>0) {
			reTries--;
			return true;
		}
		return false;
	}
	
	

}
