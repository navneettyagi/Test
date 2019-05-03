package com.qa.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class LettersAndDocs extends BaseClass
{
	String App_Id = "2216";

	@Test(priority=7)
	public void sendDocumentSupportRequest() throws InterruptedException
	{
		
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@class='rollover' and @alt='Main']"))).click().build().perform();
		
		logger.info("Hover mouse over Main menu and clicked");

		Thread.sleep(5000);
		Utility.isAlertPresent();

		Thread.sleep(2000);
		Utility.checkPageIsReady();	

		List<WebElement> workingQueue = driver.findElements(By.xpath(".//table[@id='ctl00_MainContent_wq_dg']/tbody/tr"));
		
		for(int i = 2; i<=workingQueue.size(); i++)
		{
			if(driver.findElement(By.xpath(".//table[@id='ctl00_MainContent_wq_dg']/tbody/tr["+i+"]/td[2]")).getText().equals(App_Id))
			{
				logger.info("Name with App Id "+driver.findElement(By.xpath(".//table[@id='ctl00_MainContent_wq_dg']/tbody/tr["+i+"]/td[2]")).getText()+" clicked");
				driver.findElement(By.xpath(".//table[@id='ctl00_MainContent_wq_dg']/tbody/tr["+i+"]/td[4]/a")).click();
				Thread.sleep(2000);
				Utility.isAlertPresent();
				break;
			}
		}

		

		logger.info("Navigating to Short Application page");
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		
		
		Assert.assertTrue(driver.findElement(By.id("pre_lab_lbtnLetters")).isDisplayed());
		logger.info("Navigate to Short Application page");

		driver.findElement(By.id("pre_lab_lbtnLetters")).click();
		logger.info("Letter/Docs sub menu clicked");
		Thread.sleep(5000);

		Thread.sleep(2000);
		Utility.isAlertPresent();

		Thread.sleep(2000);
		Utility.checkPageIsReady();
		
		logger.info("Navigating to Letters List page");

		Assert.assertTrue(driver.findElement(By.xpath(".//div[contains(text(),'Uploaded Documents')]")).getText().contains("Uploaded Documents"));

		logger.info("Navigate to Letters List page");
		
		driver.findElement(By.id("PDFs_ctl01_button")).click();
		
		logger.info("Request Docs support button clicked");

		Thread.sleep(5000);
		

		String mainWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();

		Iterator<String> itr= set.iterator();
		while(itr.hasNext())
		{
			String childWindow=itr.next();

			if(!mainWindow.equals(childWindow))
			{
				logger.info("New window opened");
				logger.info("Switching to new window");
				driver.switchTo().window(childWindow);
				logger.info("Switch to new window");
				driver.switchTo().window(childWindow).manage().window().maximize();
				logger.info("Maximize new window");
				Assert.assertTrue(driver.switchTo().window(childWindow).getTitle().equalsIgnoreCase("Document Support Request"));
				logger.info("Verify this is required window");
				break;
			}
		}
		
		//Fill Document Support Request
		Select requestTypeDropdown = new Select(driver.findElement(By.id("ctl00_bc_RequestType")));
		Thread.sleep(2000);
		logger.info("Request Type Dropdown is found in new window");
		requestTypeDropdown.selectByVisibleText("Enable ESIG Integration for your Organization");
		logger.info("Enable ESIG Integration for your Organization option is selected");
		
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		
		driver.findElement(By.id("ctl00_bc_CCList")).sendKeys("AAgarwal@bhavnacorp.com");
		logger.info("Other Email id is entered");
		
		Select esignDropdown = new Select(driver.findElement(By.id("ctl00_bc_ESignTypes")));
		Thread.sleep(2000);
		logger.info("ESIGN Service Provider found");
		esignDropdown.selectByVisibleText("Xpress Sign Pro (iPad)");
		logger.info("Option Xpress Sign Pro (iPad) is selected");
		
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		
		driver.findElement(By.id("ctl00_bc_Instructions")).sendKeys("Test");
		logger.info("Description / Instructions text is entered");
		
		
		driver.findElement(By.id("ctl00_bc_Attachment1")).click();
		logger.info("Browse button is clicked");
		Utility.uploadFileWithRobot(System.getProperty("user.dir")+"\\Input\\dummy.pdf");
		logger.info("Document uploaded");
		/*WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated
                ((By.id("pageTitle")))).getText().equals("Document Support Request"));*/
        Thread.sleep(2000);

		driver.findElement(By.id("ctl00_Buttons_btnSubmit")).click();
		Thread.sleep(2000);
		Utility.isAlertPresent();
		Thread.sleep(1000);
		Assert.assertTrue(driver.getWindowHandles().size()==1);
        //driver.findElement(By.xpath(".//input[@value='Close']")).click();
		//driver.close();
		driver.switchTo().window(mainWindow);
		
	}


	@Test(priority=8)
	public void verifyXmlFile() throws ParserConfigurationException, SAXException, IOException
	{
		Utility.ValidateXMLNode(System.getProperty("user.dir")+"\\Input\\demofile.xml", "employee", "Alex");
	}
	

}
