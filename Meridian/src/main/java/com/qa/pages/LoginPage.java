package com.qa.pages;

import org.openqa.selenium.By;

public class LoginPage
{	
	//@FindBy(id="ctl00_bc_LoginMain_txtLogin") public static WebElement Main_txtLogin;
	
	public static By LoginMain_txtLogin = By.id("ctl00_bc_LoginMain_txtLogin");
	
	public static By LoginMain_btnLogin = By.id("ctl00_bc_LoginMain_btnLogin");
	
	public static By MFLQuestions_lblLogin = By.id("ctl00_bc_MFLQuestions_lblLogin");
	
	public static By MFLQuestions_Answer1 = By.id("ctl00_bc_MFLQuestions_Answer1");
	
	public static By MFLQuestions_Answer2 = By.id("ctl00_bc_MFLQuestions_Answer2");
	
	public static By MFLQuestions_btnContinue = By.id("ctl00_bc_MFLQuestions_btnContinue");
	
	public static By MFLPasswordPrompt_Image = By.id("ctl00_bc_MFLPasswordPrompt_Image");
	
	public static By MFLPasswordPrompt_Password = By.id("ctl00_bc_MFLPasswordPrompt_Password");
	
	public static By MFLPasswordPrompt_btnSignIn = By.id("ctl00_bc_MFLPasswordPrompt_btnSignIn");
	
	public static By MainContent_lblWelcome = By.id("ctl00_MainContent_lblWelcome");
	
	

}
