package com.qa.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreditCard extends BaseClass
{
	String App_Id = "2216";

	@Test(priority=4)
	public void launchCreditCard() throws InterruptedException
	{
		launchApp();
		login();
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@class='rollover' and @alt='New App']"))).build().perform();
		logger.info("Hover mouse over New App menu");

		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='rollover' and @alt='New App']//following-sibling::ul/li[2]/a")));  
		// until this submenu is found
		actions.moveToElement(driver.findElement(By.xpath("//img[@class='rollover' and @alt='New App']//following-sibling::ul/li[2]/a"))).build().perform();;
		driver.findElement(By.xpath("//img[@class='rollover' and @alt='New App']//following-sibling::ul/li[2]/a")).click();

		logger.info("Hover mouse over Credit Card sub menu, and clicked");

		Thread.sleep(2000);
		Utility.isAlertPresent();

		Thread.sleep(2000);
		Utility.checkPageIsReady();

		Assert.assertTrue(driver.findElement(By.xpath(".//img[@src='/Lender/CreditCard/images/cc_section_title.gif']")).isDisplayed());

	}


	@Test(priority=5, enabled=false)
	public void fillNewUserCreditCard() throws InterruptedException
	{
		
		//Personal Information
		//Enter  SSN
		driver.findElement(By.id("sa_SSN")).sendKeys("000000003");
		logger.info("SSN Entered");
		driver.findElement(By.id("sa_FName")).clear();
		Thread.sleep(5000);
		Utility.checkPageIsReady();

		//Credit Card Information
		//Select Purpose Type
		Select purposeDropdown = new Select(driver.findElement(By.id("RequestType_RequestType")));
		Thread.sleep(2000);
		
		logger.info("Option is selected from Purpose dropdown");
		purposeDropdown.selectByIndex(3);
		Thread.sleep(2000);
		Utility.checkPageIsReady();

		//Select Card Type
		Select cardDropdown = new Select(driver.findElement(By.id("CardType_CardType")));
		Thread.sleep(2000);
		
		cardDropdown.selectByVisibleText("CREDIT CARD");
		logger.info("Option CREDIT CARD is selected from Card dropdown ");

		//Enter Requested Credit Limit
		Thread.sleep(2000);
		driver.findElement(By.id("RequestedCreditLimit")).clear();
		driver.findElement(By.id("RequestedCreditLimit")).sendKeys("10");
		logger.info("Requested Credit Limit entered");

		//Select Appropriate Box
		if(!driver.findElement(By.id("coselect_CoBorrowerType_0")).isSelected())
		{
			driver.findElement(By.id("coselect_CoBorrowerType_0")).click();
			logger.info("Individual Applicant option seected");
			Thread.sleep(2000);
			Utility.checkPageIsReady();			
		}

		//Personal Information
		//Enter  SSN
		driver.findElement(By.id("sa_SSN")).sendKeys("000000003");
		logger.info("SSN Entered");
		driver.findElement(By.id("sa_FName")).clear();
		Thread.sleep(5000);
		Utility.checkPageIsReady();

		//Enter First Name
		driver.findElement(By.id("sa_FName")).sendKeys("Alok");
		logger.info("First name entered");

		//Enter Middle Name
		driver.findElement(By.id("sa_MName")).sendKeys("");
		logger.info("Middle name entered");

		//Enter Last Name
		driver.findElement(By.id("sa_LName")).sendKeys("Agarwal");
		logger.info("Last name entered");

		//Select Suffix
		Select suffixDropdown = new Select(driver.findElement(By.id("sa_Suffix")));
		Thread.sleep(2000);
		suffixDropdown.selectByVisibleText("II");
		logger.info("Suffix selected");

		//Enter Home Phone
		driver.findElement(By.id("sa_ForeignHomePhone_tbPhoneNumber")).clear();
		driver.findElement(By.id("sa_ForeignHomePhone_tbPhoneNumber")).sendKeys("2843894849");
		logger.info("Phone number entered");

		//Enter Work Phone
		driver.findElement(By.id("sa_ForeignWorkPhone_tbPhoneNumber")).clear();
		driver.findElement(By.id("sa_ForeignWorkPhone_tbPhoneNumber")).sendKeys("2843894831");
		logger.info("Work Phone number entered");

		//Enter Ext
		driver.findElement(By.id("sa_WorkPhoneExtension")).clear();
		driver.findElement(By.id("sa_WorkPhoneExtension")).sendKeys("0120");
		logger.info("Work Phone extension number entered");

		//Enter Cell Phone
		driver.findElement(By.id("sa_ForeignCellPhone_tbPhoneNumber")).clear();
		driver.findElement(By.id("sa_ForeignCellPhone_tbPhoneNumber")).sendKeys("2222000045");
		logger.info("Cell Phone number entered");

		//Enter Email
		driver.findElement(By.id("sa_Email")).clear();
		driver.findElement(By.id("sa_Email")).sendKeys("testbh15@mailinator.com");
		logger.info("Email id entered");

		//Select Preferred Contact Method
		Select preferredContactDropdown = new Select(driver.findElement(By.id("sa_PreferredContactMethod_PreferredContactMethod")));
		Thread.sleep(2000);
		preferredContactDropdown.selectByVisibleText("EMAIL");
		logger.info("Option Email selected from Preferred Contact Dropdown");

		//Enter Date of Birth sa_DOB
		driver.findElement(By.xpath(".//input[@id='sa_DOB']//following-sibling::a/img")).click();
		Thread.sleep(2000);
		for(int i=1; i<=22; i++)
		{
			Thread.sleep(1000);
			driver.findElement(By.xpath(".//table/thead/tr[2]/td[1][text()='«']")).click();
		}

		Thread.sleep(2000);
		driver.findElement(By.xpath(".//table/tbody/tr/td[text()='10']")).click();
		logger.info("Date of Birth entered");
		Thread.sleep(2000);

		//Select Citizenship
		Select citizenshipDropdown = new Select(driver.findElement(By.id("sa_Citizenship")));
		Thread.sleep(2000);
		citizenshipDropdown.selectByVisibleText("US CITIZEN");
		logger.info("Option US CITIZEN is selected from Citizenship Dropdown");

		//Current Address
		if(!driver.findElement(By.id("sa_curradd_IsThreeLineAddress_0")).isSelected())
		{
			driver.findElement(By.id("sa_curradd_IsThreeLineAddress_0")).click();
			logger.info("Option Domestic is selected for Current Address radio button");
			Thread.sleep(2000);			
		}

		//Enter Address
		driver.findElement(By.id("sa_curradd_Address")).sendKeys("Test");
		logger.info("Current Address entered");

		//To allow Address
		driver.findElement(By.id("sa_curradd_Zip")).clear();
		Thread.sleep(2000);

		Utility.isAlertPresent();

		Thread.sleep(2000);
		Utility.checkPageIsReady();

		//Enter Zip
		driver.findElement(By.id("sa_curradd_Zip")).sendKeys("90001");
		logger.info("Zip Code entered");
		Thread.sleep(1000);
		driver.findElement(By.id("sa_curradd_County")).clear();
		Thread.sleep(1000);
		Utility.isAlertPresent();

		//Enter City
		//driver.findElement(By.id("sa_curradd_City")).sendKeys("Los Angeles");

		//Select State
		Select stateDropdown = new Select(driver.findElement(By.id("sa_curradd_State")));
		Thread.sleep(2000);
		stateDropdown.selectByVisibleText("CO");

		Thread.sleep(5000);
		Utility.checkPageIsReady();

		//Enter County
		driver.findElement(By.id("sa_curradd_County")).sendKeys("Test County");
		logger.info("County entered");

		//Select Occupancy Status
		Select occupancyDropdown = new Select(driver.findElement(By.id("sa_OccupancyStatus")));
		Thread.sleep(2000);
		occupancyDropdown.selectByValue("RENT");
		logger.info("Option RENT is selected from Occupancy Dropdown");

		//Occupancy Duration

		//Select Occupancy Months
		driver.findElement(By.id("sa_OccupancyDuration_Year")).clear();
		Thread.sleep(2000);
		Select occupancyMonthsDropdown = new Select(driver.findElement(By.id("sa_OccupancyDuration_Month")));
		Thread.sleep(2000);
		logger.info("Occupancy Months is selected from Occupancy Months Dropdown");
		occupancyMonthsDropdown.selectByValue("6");


		//Enter Occupancy Years
		Thread.sleep(2000);
		driver.findElement(By.id("sa_OccupancyDuration_Year")).clear();
		driver.findElement(By.id("sa_OccupancyDuration_Year")).sendKeys("2");
		logger.info("Occupancy duration Years entered");

		driver.findElement(By.id("sa_Employer")).clear();
		Thread.sleep(5000);

		//Enter Housing Payment
		driver.findElement(By.id("sa_MonthlyHousingCost")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("sa_MonthlyHousingCost")).sendKeys("500");
		logger.info("Housing Payment entered");

		driver.findElement(By.id("sa_Employer")).clear();
		Thread.sleep(2000);

		//Employment Information
		//Select Employment Status
		Select employeeStatus = new Select(driver.findElement(By.id("sa_EmploymentStatus")));
		Thread.sleep(2000);
		employeeStatus.selectByVisibleText("EMPLOYED");
		Thread.sleep(5000);
		logger.info("Option EMPLOYED is selected from Employee Status dropdown");

		//Enter Profession/Job Title
		driver.findElement(By.id("sa_Occupation_tbOccupation")).sendKeys("Test Engineer");
		logger.info("Profession/Job Title entered");

		//Enter Employer
		driver.findElement(By.id("sa_Employer")).sendKeys("Test Employer");
		logger.info("Employer entered");
		Thread.sleep(2000);

		//Employed Duration

		//Select Employed Months
		driver.findElement(By.id("sa_EmployedMonths_Year")).clear();
		Thread.sleep(5000);
		Select employedMonthsDropdown = new Select(driver.findElement(By.id("sa_EmployedMonths_Month")));
		Thread.sleep(2000);
		logger.info("Employed Months is selected from Employed Months Dropdown");
		employedMonthsDropdown.selectByVisibleText("7");


		//Enter Employed Years
		driver.findElement(By.id("sa_EmployedMonths_Year")).clear();
		Thread.sleep(5000);
		driver.findElement(By.id("sa_EmployedMonths_Year")).sendKeys("2");
		logger.info("Employed duration Years entered");
		Thread.sleep(3000);

		//MLA: Is Covered Borrower
		driver.findElement(By.id("sa_MLAVerification_MLACoveredDate")).clear();
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//input[@id='sa_MLAVerification_MLACoveredDate']//following-sibling::a/img")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//table/thead/tr[2]/td[1][text()='»']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//table/tbody/tr/td[text()='10']")).click();
		logger.info("MLA: Is Covered Borrower, date entered");

		//Income Information

		//Gross Monthly Income
		WebElement grossMonthlyIncome = driver.findElement(By.id("sa_MonthlyIncomeBaseSalary"));
		grossMonthlyIncome.clear();
		grossMonthlyIncome.sendKeys("5000");
		logger.info("Monthly Income entered");

		//Enter Income Verify
		driver.findElement(By.id("sa_IncomeVerifyMethod_IncomeVerify")).sendKeys("Yes");
		logger.info("Monthly Income Verified entered");

		//Check Tax Exempt		
		driver.findElement(By.id("sa_IsTaxExemptBaseSalary")).click();	
		logger.info("Tax Exempt checkbox selected");

		driver.findElement(By.id("CQuest_rpt_ctl01_SingleCustomQuestion_chkAnswer_1")).click();	
		logger.info("Custom Questions checkbox selected");
		Thread.sleep(5000);
		Utility.checkPageIsReady();
		driver.findElement(By.id("runCreditbtn_btnSavePullCredit")).click();
		logger.info("Pull Credit and Save button clicked");

		Thread.sleep(5000);
		Utility.checkPageIsReady();


		Assert.assertTrue(driver.findElement(By.id("TAB_divReferredProducts")).isDisplayed());
		//logout();
	}


	
	
	@Test(priority=6)
	public void fillCreditCardForm() throws InterruptedException
	{

		driver.findElement(By.id("sa_SSN")).sendKeys("000000003");
		driver.findElement(By.id("sa_FName")).click();
		Thread.sleep(5000);
		Utility.checkPageIsReady();

		//Purpose type
		
		Select purposeDropdown = new Select(driver.findElement(By.id("RequestType_RequestType")));
		Utility.checkDuplicateItem(purposeDropdown.getOptions());
		purposeDropdown.selectByIndex(3);
		Thread.sleep(5000L);
		logger.info("Purpose Dropdown option selected");

		//How did you find
		if(!driver.findElement(By.id("CQuest_rpt_ctl01_SingleCustomQuestion_chkAnswer_1")).isSelected())
		{
			driver.findElement(By.id("CQuest_rpt_ctl01_SingleCustomQuestion_chkAnswer_1")).click();
			logger.info("Custom Questions checkbox selected");
			Thread.sleep(5000);
			Utility.checkPageIsReady();
		}


		//Pull credit/Save
		driver.findElement(By.id("runCreditbtn_btnSavePullCredit")).click();
		logger.info("Pull Credit and Save button clicked");


		/*innerloop:for(int i=1; i<=3; i++)
		{
			try {
				Thread.sleep(5000);
				Assert.assertTrue(driver.findElement(By.id("TAB_divReferredProducts")).isDisplayed());
				if(driver.findElement(By.id("TAB_divReferredProducts")).isDisplayed())
				{
					break innerloop;
				}

			} catch (NoSuchElementException e) {
				System.out.println("Not Found");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}*/

		Thread.sleep(5000);
		Utility.checkPageIsReady();

		Assert.assertTrue(driver.findElement(By.id("TAB_divReferredProducts")).isDisplayed());


	}

	
	

}
