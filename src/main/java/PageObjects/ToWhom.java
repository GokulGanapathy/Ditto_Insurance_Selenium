package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToWhom {
	
	public WebDriver driver;
	
	public ToWhom(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//p/span[contains(text(),'You')])[1]")
	WebElement you;
	
	@FindBy(xpath="//button/span[text()='Continue']")
	WebElement continueEle;
	
	public AboutYouAndPlanType selectYouAndContinue() {
		you.click();
		continueEle.click();
		
		AboutYouAndPlanType aboutYou = new AboutYouAndPlanType(driver);
		return aboutYou;
	}

}
