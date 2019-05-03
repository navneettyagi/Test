package com.qa.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseClass
{
	
	@Test(priority=1)
	public void launchAppTest()
	{
		launchApp();
	}


	@Test(priority=2, enabled=false)
	public void invalidLoginTest() throws InterruptedException
	{
		driver.navigate().to("https://beta.loanspq.com/login.aspx?enc2=lqc_NUOh2_sZoFnhMuzKAiuDjxZG8O04St2Hlzvvbvs");
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		driver.findElement(By.id("ctl00_bc_LoginMain_txtLogin")).sendKeys("ALOKA_QA_BH");
		logger.info("User name entered");
		driver.findElement(By.name("ctl00$bc$LoginMain$btnLogin")).click();
		logger.info("Login button Clicked");
		Utility.checkPageIsReady();
		logger.info("Navigate to Computer recognize page");
		Assert.assertTrue(driver.findElement(By.id("ctl00_bc_MFLQuestions_lblLogin")).getText().equalsIgnoreCase("ALOKA_QA_BH"));
		logger.info("Computer recognize page Verified");
		driver.findElement(By.id("ctl00_bc_MFLQuestions_Answer1")).sendKeys("jhansi");
		logger.info("Answer1 has entered");
		driver.findElement(By.id("ctl00_bc_MFLQuestions_Answer2")).sendKeys("jhansi");
		logger.info("Answer2 has entered");
		driver.findElement(By.id("ctl00_bc_MFLQuestions_btnContinue")).click();
		logger.info("Continue button clicked");
		Utility.checkPageIsReady();
		logger.info("Navigating to Password verification page");
		Assert.assertTrue(driver.findElement(By.id("ctl00_bc_MFLPasswordPrompt_Image")).isDisplayed());
		logger.info("Password page verified");
		driver.findElement(By.id("ctl00_bc_MFLPasswordPrompt_Password")).sendKeys("password@3");
		logger.info("Password entered");
		driver.findElement(By.id("ctl00_bc_MFLPasswordPrompt_btnSignIn")).click();
		logger.info("Sign In button Clicked");
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		Assert.assertTrue(driver.findElement(By.id("ctl00_MainContent_lblWelcome")).isDisplayed());
		logger.info("User Home page verified");
		logger.info("User Login successfully");		
	}

	
	@Test(priority=3)
	public void logintest() throws InterruptedException
	{

		login();
	}

}
