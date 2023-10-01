package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import net.bytebuddy.build.Plugin.Engine.Source.Empty;

import org.apache.commons.io.FileUtils;


//import shadedwipo.org.apache.commons.io.FileUtils;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	private static final String OUTPUT_FOLDER = "./screenshot/";

	public static String highlight;
	/*
	 Create an Local Copy of Driver
	 Set the Driver with TL
	 getdriver() -- driver
	 We can Take the driver copy anywhere in the framework
	 Better thread management
	 To avoid the deadlock local condition - Tl driver Copy
	 
	 */
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();	
	public static boolean alreadyExecuted = false;
	
	/***
	 * This method is used to initialize the driver using browser name
	 * @param browserName
	 * @return this returns Webdriver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		System.out.println("Browser name is " + browserName);
		optionsManager = new OptionsManager(prop);
	    if(!alreadyExecuted) {
		try {
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "\\screenshot\\"));
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "\\allure-results\\"));
			alreadyExecuted = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		if(browserName.equalsIgnoreCase("chrome")) {
//			driver =new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
//			driver= new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));					
		}
		else if(browserName.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("Please pass the Correct Driver");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	/***
	 * this method is used to initialize the properties
	 * @return this returns the properties class reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("src\\test\\resources\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
/***
 * 
 * @return
 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
//	public static String getScreenshot(String methodName) {
//		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
//				+ ".png";
//		System.out.println("user directory: " + System.getProperty("user.dir"));
//		System.out.println("screenshot path: " + path);
//		File destination = new File(path);
//		try {
//			FileHandler.copy(srcFile, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return path;
//	}
	public void clearDirectoy() {
		try {
			FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "\\screenshot\\"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshot\\" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
