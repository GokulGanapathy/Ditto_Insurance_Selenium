package Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class dittoInsuranceStandAloneTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://app.joinditto.in/fq");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String insuranceOption = "Term";
		WebElement insurance = driver.findElement(By.xpath("//div/p[contains(text(),'"+insuranceOption+"')]"));
		WebElement options = driver.findElement(with(By.tagName("div")).near(insurance));
		List<WebElement> insurancePlans = options.findElements(By.xpath("//p/span"));
		for(WebElement plan :insurancePlans) {
			if(plan.getText().contains("ReAssure 2.0")) {
				plan.click();
				break;
			}
		}
		driver.findElement(By.xpath("(//p/span[contains(text(),'You')])[1]")).click();
		driver.findElement(By.xpath("//button/span[text()='Continue']")).click();
		
		driver.findElement(By.id("text-input-Selfage")).sendKeys("24");
		driver.findElement(By.cssSelector("[placeholder='Pin code']")).sendKeys("627719");
		driver.findElement(By.xpath("//div[text()='Select your plan']")).click();
		
		List<WebElement> planType = driver.findElements(By.cssSelector("ul li[role='option']"));
		for(WebElement plan : planType) {
			if(plan.isDisplayed()) {
				if(plan.getText().contains("Platinum")){
					plan.click();
				}
			}
		}
		
		List<WebElement> features = driver.findElements(By.xpath("//img//following-sibling::div/p/span"));
		System.out.println("________-----------------____________");
		for(int i=0;i<features.size();) {
			System.out.println(features.get(i).getText());
			++i;
			System.out.println(features.get(i).getText());
			++i;
			System.out.println("________-----------------____________");
		}
		
		
		List<WebElement> premiumOptions = driver.findElements(By.xpath("//div[contains(@class,'rec-swipable')]/div/div/div"));
		for(WebElement premium :premiumOptions ) {
			if(premium.getText().equals("₹20 L")) {
				premium.click();
				break;
			}
			System.out.println(premium.getText());
		}
		
		driver.findElement(By.xpath("//button/span[text()='Calculate Premium']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Year')]")));
		
		List<WebElement> policyPeriod = driver.findElements(By.xpath("//span[contains(text(),'Year')]"));
		for(WebElement period : policyPeriod) {
			if(period.getText().equals("2 Years")) {
				period.click();
				break;
			}
		}
		
		String basePremium = driver.findElement(By.xpath("//p[contains(text(),'Base Premium')]/parent::div/following-sibling::div/p")).getText();
		WebElement addOns = driver.findElement(By.xpath("//span[contains(text(),'Add-ons')]/parent::p/following-sibling::div[1]"));
		List<WebElement> addOnsCheck = driver.findElements(By.cssSelector("input[type='checkbox']"));
		
		List<String> addOnsNeeded = new ArrayList<>();
		addOnsNeeded.add("Hospital Cash Benefit");
		List<WebElement> addOnsavailable=driver.findElements(By.xpath("//span[contains(text(),'Add-ons')]/parent::p/following-sibling::div[1]/div"));
		for(int i=0;i<addOnsavailable.size();++i) {
			String addOn = addOnsavailable.get(i).findElement(By.cssSelector("div p")).getText();
			System.out.println("The addon : "+addOn);
			if(addOnsNeeded.contains(addOn)) {
				System.out.println("The addon : "+addOn);
				addOnsavailable.get(i).findElement(By.xpath("//div/descendant::input[@name='"+addOn+"']/parent::span")).click();
			}
		}
		
		double addOnsd=0;
		for(int i=0;i<addOnsCheck.size();++i) {
			if(addOnsCheck.get(i).isSelected()) {
				String addOnsAmt =addOns.findElement(By.xpath("(//p[contains(text(),'₹')])["+(i+1)+"]")).getText();
				System.out.println(addOnsAmt);
				addOnsAmt=addOnsAmt.replaceAll("₹", "");
				addOnsAmt=addOnsAmt.replaceAll(",", "");
				addOnsd = addOnsd+Double.parseDouble(addOnsAmt);
			}
		}
		String gst = driver.findElement(By.xpath("//p[contains(text(),'GST')]/parent::div/following-sibling::div/p")).getText();
		String totalPremium = driver.findElement(By.xpath("//p[contains(text(),'Total premium')]/parent::div/following-sibling::div/p")).getText();
		
		System.out.println("base : "+ basePremium);
		System.out.println("GST : "+ gst);
		System.out.println("Total : "+ totalPremium);
		basePremium=basePremium.replaceAll("₹", "");
		basePremium=basePremium.replaceAll(",", "");
		gst=gst.replaceAll("₹", "");
		gst=gst.replaceAll(",", "");
		totalPremium=totalPremium.replaceAll("₹", "");
		totalPremium=totalPremium.replaceAll(",", "");
		
		double gstd = Double.parseDouble(gst);
		double basepremiumd = Double.parseDouble(basePremium);
		double totPremiumd = Double.parseDouble(totalPremium);
		
		System.out.println(gstd);
		System.out.println(basepremiumd);
		System.out.println(totPremiumd);
		System.out.println(addOnsd);
		
		Assert.assertEquals(gstd+basepremiumd+addOnsd, totPremiumd);
		//driver.quit();
	}

}
