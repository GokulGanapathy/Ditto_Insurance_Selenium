package PageObjects;


import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class InsuranceSelectPage {
	
	public WebDriver driver;
	public InsuranceSelectPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	WebElement insurance;
	public void insuranceOption(String InsuranceOption) {
		insurance = driver.findElement(By.xpath("//div/p[contains(text(),'"+InsuranceOption+"')]"));
	}
	 
	
	public ToWhom selectInsurance(String InsuranceOption, String InsurancePlan) {
		insuranceOption(InsuranceOption);
		WebElement options = driver.findElement(with(By.tagName("div")).near(insurance));
		List<WebElement> insurancePlans = options.findElements(By.xpath("//p/span"));
		for(WebElement plan :insurancePlans) {
			if(plan.getText().contains(InsurancePlan)) {
				plan.click();
				break;
			}
		}
		ToWhom toWhom = new ToWhom(driver);
		return toWhom;
	}
	
	
	

}
