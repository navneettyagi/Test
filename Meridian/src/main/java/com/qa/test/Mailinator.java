package com.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Mailinator extends BaseClass
{
	@Test
	public void launchMailinator()
	{
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://www.mailinator.com/");
		logger.info("URL Launched");
		driver.manage().window().maximize();
		logger.info("Window maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utility.checkPageIsReady();
		Assert.assertTrue(driver.findElement(By.xpath(".//img[contains(@src,'mailinatorguy.png')]")).isDisplayed());
		
		driver.findElement(By.id("inboxfield")).sendKeys("test1122"+Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
