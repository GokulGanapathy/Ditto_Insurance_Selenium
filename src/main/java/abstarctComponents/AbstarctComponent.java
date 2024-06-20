package abstarctComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstarctComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstarctComponent(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	public void presenceOfElementWait(By eleLocator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(eleLocator));
	}

}
