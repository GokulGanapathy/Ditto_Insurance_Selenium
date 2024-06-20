package Cucumber_Framework;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import PageObjects.AboutYouAndPlanType;
import PageObjects.GetFeaturesAndSelectCoverage;
import PageObjects.InsuranceSelectPage;
import PageObjects.PremiumPage;
import PageObjects.ToWhom;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Ditto_Insurance_StepDefinitions extends BaseTest {
	
	InsuranceSelectPage insuranceSelect;
	AboutYouAndPlanType aboutYou;
	ToWhom toWhom;
	GetFeaturesAndSelectCoverage getFeaturesAndSelectCoverage;
	PremiumPage premiumPage;
	
	@Given("Open the browser")
	public void Open_the_browser() {
		insuranceSelect = invokeBrowser();
	}

	@Given("Select the desired Insurance Plan")
	public void select_the_desired_insurance_plan() {
		toWhom = insuranceSelect.selectInsurance("Term","ReAssure 2.0");
		
	}

	@When("Select to whom we need to buy Insurance")
	public void select_to_whom_we_need_to_buy_insurance() {
		aboutYou = toWhom.selectYouAndContinue();
		
	}

	@When("Enter the basic Details required")
	public void enter_the_basic_details_required() {
		aboutYou.sendBasicInputs("24","627719");
		getFeaturesAndSelectCoverage = aboutYou.selectPlan("Platinum");
	    
	}

	@Then("click on Calculate the premium and select addons Required if any")
	public void click_on_calculate_the_premium_and_select_addons_required_if_any() {
		getFeaturesAndSelectCoverage.getFeaturesList();
		getFeaturesAndSelectCoverage.selectCoverageAmt("₹20 L");
		premiumPage = getFeaturesAndSelectCoverage.calculatePremium();
	}

	@Then("Validate the Total premium with other premium sum")
	public void validate_the_total_premium_with_other_premium_sum() {
		List<String> AddOnsList = new ArrayList<>();
		AddOnsList.add("Hospital Cash Benefit");
		
		premiumPage.selectPolicyPeriod("2 Years");
		//premiumPage.chooseAddOns(AddOnsList);
		//premiumPage.getAddOnsPrice();
		double allAmounts = premiumPage.getGSTandPremiums();
		double totalPremium = premiumPage.getTotalPremium();
		
		Assert.assertEquals(allAmounts, totalPremium);
	}
	
	@Then("Close the browser")
	public void Close_the_browser() {
		driver.quit();
	}



}
