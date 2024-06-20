package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutYouAndPlanType {

	WebDriver driver;
	public AboutYouAndPlanType(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="text-input-Selfage")
	WebElement age;
	
	@FindBy(css="[placeholder='Pin code']")
	WebElement pinCode;
	
	@FindBy(xpath="//div[text()='Select your plan']")
	WebElement planOption;
	
	@FindBy(css="ul li[role='option']")
	List<WebElement> planType;
	
	public void sendBasicInputs(String Age,String PinCode) {
		age.sendKeys(Age);
		pinCode.sendKeys(PinCode);
		
	}
	
	public GetFeaturesAndSelectCoverage selectPlan(String Insuranceplan) {
		planOption.click();
		for(WebElement plan : planType) {
			if(plan.isDisplayed()) {
				if(plan.getText().contains(Insuranceplan)){
					plan.click();
				}
			}
		}
		
		return new GetFeaturesAndSelectCoverage(driver);
	}
}
