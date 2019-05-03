package com.qa.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utility extends BaseClass
{

	//Code to handle page load
	public static void checkPageIsReady() 
	{

		JavascriptExecutor js = (JavascriptExecutor)driver;


		//Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete"))
		{ 
			return; 
		} 

		//This loop will rotate for 25 times to check If page Is ready after every 1 second.
		//You can replace your value with 25 If you wants to Increase or decrease wait time.
		for (int i=0; i<25; i++)
		{ 
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {} 
			//To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				break; 
			}   
		}
	}


	//Alert handling
	public static void isAlertPresent()
	{ 
		try{ 
			Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
			if(a!=null){
				logger.info("Alert! "+driver.switchTo().alert().getText()+" Is Handled");
				driver.switchTo().alert().accept();

			}else{
				throw new Throwable();
			}
		} 
		catch (Throwable e) {

		} 

	}


	//File upload by Robot Class
	public static void uploadFileWithRobot (String imagePath) {
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	// Method to take screenshot
	public static String takeScreenShot(String methodname)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\Output\\Screenshot\\"+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}	


	public static void checkDuplicateItem(List<WebElement> list)
	{
		boolean flag = false;
		/*Select select = new Select(driver.findElement(By.xpath(path)));

		List<WebElement> list = select.getOptions();*/

		for(int i = 0; i<list.size(); i++)
		{
			System.out.println(list.get(i).getText());
		}


		Set<String> listNames = new HashSet<String>(list.size());
		for (WebElement w : list) {
			listNames.add(w.getText().trim());
		}

		if(list.size() == listNames.size())
		{
			flag = true;
		}

		Assert.assertTrue(flag);
	}

	public static void ValidateXMLNode(String xmlpath, String tagname, String tagvalue) throws ParserConfigurationException, SAXException, IOException
	{ 
		boolean flag = false;
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		//Build Document
		Document document = builder.parse(xmlpath);

		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		//Get all employees
		NodeList nList = document.getElementsByTagName(tagname);

		/*for (int i = 0; i < nList.getLength(); i++)
		{
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				//Print each employee's detail
				Element eElement = (Element) node;
				if(eElement.getElementsByTagName(tagname).item(0).getTextContent().equalsIgnoreCase(tagvalue))
				{
					flag=true;
					break;
				}
			}
		}*/

		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node node = nList.item(temp);
			System.out.println("");    //Just a separator
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				//Print each employee's detail
				Element eElement = (Element) node;
				
				if(eElement.getElementsByTagName("firstName").item(0).getTextContent().equalsIgnoreCase(tagvalue))
				{
					System.out.println("Employee id : "    + eElement.getAttribute("id"));
					System.out.println("First Name : "  + eElement.getElementsByTagName("firstName").item(0).getTextContent());
					System.out.println("Last Name : "   + eElement.getElementsByTagName("lastName").item(0).getTextContent());
					System.out.println("Location : "    + eElement.getElementsByTagName("location").item(0).getTextContent());
					flag=true;
					break;
				}
				
				
			}
		}
		Assert.assertTrue(flag);

	}



}
