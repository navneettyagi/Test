package com.qa.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.pages.LoginPage;

public class BaseClass 
{
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	String meredianURL, userName, question1, question2, password;

	@BeforeSuite
	public static void initialSetUp()
	{
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\LogGeneration.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		
	}
	
	
	
	@BeforeMethod
	public void extentReport(Method method)
	{
		logger = extent.createTest(method.getName());
	}
	
	
	@AfterMethod
	public void checkStatus(ITestResult result) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp = Utility.takeScreenShot(result.getTestName());
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
		extent.flush();
	}
	
	public void selectEnvironment()
	{
		File file = new File(System.getProperty("user.dir")+"\\Input\\Config.properties");
		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
		
		/*System.out.println("Please enter Your test execution environment i.e., DEV, QA, or UAT:");
	    Scanner scan = new Scanner(System.in);
	    String line = scan.nextLine();*/
	    
	    if(prop.getProperty("Environment").equalsIgnoreCase("QA"))
	    {
	    	meredianURL = "https://beta.loanspq.com/login.aspx?enc2=lqc_NUOh2_sZoFnhMuzKAiuDjxZG8O04St2Hlzvvbvs";	
	    	userName = "ALOKA_QA_BH";
	    	question1 = "jhansi";
	    	question2 = "jhansi";
	    	password = "password@2";
	    }
	    else if(prop.getProperty("Environment").equalsIgnoreCase("DEV"))
	    {
	    	meredianURL = "https://beta.loanspq.com/login.aspx?enc2=lqc_NUOh2_sZoFnhMuzKAiuDjxZG8O04St2Hlzvvbvs";
	    	userName = "ALOKA_QA_BH";
	    	question1 = "jhansi";
	    	question2 = "jhansi";
	    	password = "password@2";
	    }
	    else if(prop.getProperty("Environment").equalsIgnoreCase("UAT")) 
	    {
	    	meredianURL = "https://beta.loanspq.com/login.aspx?enc2=lqc_NUOh2_sZoFnhMuzKAiuDjxZG8O04St2Hlzvvbvs";
	    	userName = "ALOKA_QA_BH";
	    	question1 = "jhansi";
	    	question2 = "jhansi";
	    	password = "password@2";
	    }
	    else
	    {
	    	System.out.println("Please enter a Valid Environment in Config File!!!");
	    	//selectEnvironment();
	    }
	    
	    if(prop.getProperty("Browser").equalsIgnoreCase("IE"))
	    {
	    	System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			
			DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
			ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, meredianURL);
			driver = new InternetExplorerDriver(ieCaps);
	    }
	    else if(prop.getProperty("Browser").equalsIgnoreCase("FireFox"))
	    {
	    	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
	    	driver = new FirefoxDriver();
	    	driver.get(meredianURL);
	    }
	    else if(prop.getProperty("Browser").equalsIgnoreCase("Chrome"))
	    {
	    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
	    	driver = new ChromeDriver();
	    	driver.get(meredianURL);
	    }
	    else
	    {
	    	System.out.println("Please enter a Valid Browser in Config File!!!");
	    }
	    
	    
	}
	
	
	
	public void launchApp()
	{
		selectEnvironment();
				
		logger.info("URL Launched");
		driver.manage().window().maximize();
		logger.info("Window maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utility.checkPageIsReady();
		Assert.assertEquals(driver.getTitle(), "LoansPQ & Xpress Accounts Login");
		logger.info("Login page verified");
		
	}
	
	public void login() throws InterruptedException
	{
		driver.navigate().to("https://beta.loanspq.com/login.aspx?enc2=lqc_NUOh2_sZoFnhMuzKAiuDjxZG8O04St2Hlzvvbvs");
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		driver.findElement(LoginPage.LoginMain_txtLogin).sendKeys(userName);
		logger.info("User name entered");
		
		driver.findElement(LoginPage.LoginMain_btnLogin).click();
		logger.info("Login button Clicked");
		Utility.checkPageIsReady();
		logger.info("Navigate to Computer recognize page");
		
		Assert.assertTrue(driver.findElement(LoginPage.MFLQuestions_lblLogin).getText().equalsIgnoreCase(userName));
		logger.info("Computer recognize page Verified");
		
		driver.findElement(LoginPage.MFLQuestions_Answer1).sendKeys(question1);
		logger.info("Answer1 has entered");
		
		driver.findElement(LoginPage.MFLQuestions_Answer2).sendKeys(question2);
		logger.info("Answer2 has entered");
		
		driver.findElement(LoginPage.MFLQuestions_btnContinue).click();
		logger.info("Continue button clicked");
		Utility.checkPageIsReady();
		logger.info("Navigate to Password verification page");
		
		Assert.assertTrue(driver.findElement(LoginPage.MFLPasswordPrompt_Image).isDisplayed());
		logger.info("Password page verified");
		
		driver.findElement(LoginPage.MFLPasswordPrompt_Password).sendKeys(password);
		logger.info("Password entered");
		
		driver.findElement(LoginPage.MFLPasswordPrompt_btnSignIn).click();
		logger.info("Sign In button Clicked");
		Thread.sleep(2000);
		Utility.checkPageIsReady();
		
		Assert.assertTrue(driver.findElement(LoginPage.MainContent_lblWelcome).isDisplayed());
		logger.info("User Home page verified");
		logger.info("User Login successfully");
	}
	
	public void logout() throws InterruptedException
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[contains(@id,'ibtnLogout')]")));
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[contains(@id,'ibtnLogout')]")).click();
		logger.info("Logout button clicked");
		Thread.sleep(2000);
		Utility.isAlertPresent();
		Thread.sleep(2000);
		//driver.navigate().refresh();
		Utility.checkPageIsReady();
		//Assert.assertEquals(driver.getTitle(), "LoansPQ & Xpress Accounts Login");
		logger.info("User is logged out successfully");	
		logger.info("Application navigate back to Login page");
	}

	
	
	/*@AfterSuite
	public void tearDown()
	{
		driver.close();
	}*/
		
}
