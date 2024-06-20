package Tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AboutYouAndPlanType;
import PageObjects.GetFeaturesAndSelectCoverage;
import PageObjects.InsuranceSelectPage;
import PageObjects.PremiumPage;
import PageObjects.ToWhom;
import TestComponents.BaseTest;

public class InsurancePremiumTest extends BaseTest{
	
	@Test
	public void restAssureNivaTest() {
		
		InsuranceSelectPage insuranceSelect = invokeBrowser();
		
		ToWhom toWhom = insuranceSelect.selectInsurance("Term","ReAssure 2.0");
		AboutYouAndPlanType aboutYou = toWhom.selectYouAndContinue();
		
		aboutYou.sendBasicInputs("24","627719");
		GetFeaturesAndSelectCoverage getFeaturesAndSelectCoverage = aboutYou.selectPlan("Platinum");
		
		getFeaturesAndSelectCoverage.getFeaturesList();
		getFeaturesAndSelectCoverage.selectCoverageAmt("₹20 L");
		PremiumPage premiumPage = getFeaturesAndSelectCoverage.calculatePremium();
		
		List<String> AddOnsList = new ArrayList<>();
		AddOnsList.add("Hospital Cash Benefit");
		
		premiumPage.selectPolicyPeriod("2 Years");
		//premiumPage.chooseAddOns(AddOnsList);
		//premiumPage.getAddOnsPrice();
		double allAmounts = premiumPage.getGSTandPremiums();
		double totalPremium = premiumPage.getTotalPremium();
		
		Assert.assertEquals(allAmounts, totalPremium);
		
	}

}
