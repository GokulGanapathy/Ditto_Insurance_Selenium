package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstarctComponents.AbstarctComponent;

public class GetFeaturesAndSelectCoverage extends AbstarctComponent{
	
	WebDriver driver;
	public  GetFeaturesAndSelectCoverage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img//following-sibling::div/p/span")
	List<WebElement> features;
	
	@FindBy(xpath="//div[contains(@class,'rec-swipable')]/div/div/div")
	List<WebElement> premiumOptions;
	
	@FindBy(xpath="//button/span[text()='Calculate Premium']")
	WebElement CalculatePremium;
	
	By year = By.xpath("//span[contains(text(),'Year')]");
	
	
	public void getFeaturesList() {
		System.out.println("________-----------------____________");
		for(int i=0;i<features.size();) {
			System.out.println(features.get(i).getText());
			++i;
			System.out.println(features.get(i).getText());
			++i;
			System.out.println("________-----------------____________");
		}
	}
	
	public void selectCoverageAmt(String coverAmt) {
		for(WebElement premium :premiumOptions ) {
			if(premium.getText().equals(coverAmt)) {
				premium.click();
				break;
			}
			System.out.println(premium.getText());
		}
		
	}
	
	public PremiumPage calculatePremium() {
		CalculatePremium.click();
		presenceOfElementWait(year);
		PremiumPage premiumPage = new PremiumPage(driver);
		return premiumPage;
	}
	
	
	
}
