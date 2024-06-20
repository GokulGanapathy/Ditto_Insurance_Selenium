package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PremiumPage {
	
	WebDriver driver;
	public PremiumPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Year')]")
	List<WebElement> policyPeriod;
	
	@FindBy(xpath="//p[contains(text(),'Base Premium')]/parent::div/following-sibling::div/p")
	WebElement basePremium;
	
	@FindBy(xpath="//span[contains(text(),'Add-ons')]/parent::p/following-sibling::div[1]")
	WebElement addOns;
	
	@FindBy(css="input[type='checkbox']")
	List<WebElement> addOnsCheckBox;
	
	@FindBy(xpath="//span[contains(text(),'Add-ons')]/parent::p/following-sibling::div[1]/div")
	List<WebElement> addOnsavailable;
	
	@FindBy(xpath="//p[contains(text(),'GST')]/parent::div/following-sibling::div/p")
	WebElement gst;
	
	@FindBy(xpath="//p[contains(text(),'Total premium')]/parent::div/following-sibling::div/p")
	WebElement totalPremium ;
	
	double addOnsd=0;
	
	public void selectPolicyPeriod(String PolicyPeriod) {
		for(WebElement period : policyPeriod) {
			if(period.getText().equals(PolicyPeriod)) {
				period.click();
				break;
			}
		}
	}
	
	public void chooseAddOns(List<String> addOnsNeeded) {
		for(int i=0;i<addOnsavailable.size();++i) {
			String addOn = addOnsavailable.get(i).findElement(By.cssSelector("div p")).getText();
			System.out.println("The addon : "+addOn);
			if(addOnsNeeded.contains(addOn)) {
				System.out.println("The addon : "+addOn);
				addOnsavailable.get(i).findElement(By.xpath("//div/descendant::input[@name='"+addOn+"']/parent::span")).click();
			}
		}
	}
	
	public void getAddOnsPrice() {
		
		for(int i=0;i<addOnsCheckBox.size();++i) {
			if(addOnsCheckBox.get(i).isSelected()) {
				String addOnsAmt = addOns.findElement(By.xpath("(//p[contains(text(),'₹')])["+(i+1)+"]")).getText();
				System.out.println(addOnsAmt);
				addOnsAmt=addOnsAmt.replaceAll("₹", "");
				addOnsAmt=addOnsAmt.replaceAll(",", "");
				addOnsd = addOnsd+Double.parseDouble(addOnsAmt);
				System.out.println("AddOns : "+addOnsd);
			}
		}
	}

	public double getGSTandPremiums() {
		
		String basePremiumAmt = basePremium.getText();
		String gstAmt = gst.getText();
		
		System.out.println("base : "+ basePremiumAmt);
		System.out.println("GST : "+ gstAmt);
		
		basePremiumAmt=basePremiumAmt.replaceAll("₹", "");
		basePremiumAmt=basePremiumAmt.replaceAll(",", "");
		
		gstAmt=gstAmt.replaceAll("₹", "");
		gstAmt=gstAmt.replaceAll(",", "");
		
		double basepremiumd = Double.parseDouble(basePremiumAmt);
		double gstd = Double.parseDouble(gstAmt);
		
		System.out.println(gstd);
		System.out.println(basepremiumd);
		System.out.println(addOnsd);
		
		return (gstd+basepremiumd+addOnsd);
	}
	
	public double getTotalPremium() {
		String totalPremiumAmt = totalPremium.getText();
		System.out.println("Total : "+ totalPremiumAmt);
		
		totalPremiumAmt=totalPremiumAmt.replaceAll("₹", "");
		totalPremiumAmt=totalPremiumAmt.replaceAll(",", "");
		
		double totPremiumd = Double.parseDouble(totalPremiumAmt);
		System.out.println(totPremiumd);
		
		return totPremiumd;
		
	}
	
}
