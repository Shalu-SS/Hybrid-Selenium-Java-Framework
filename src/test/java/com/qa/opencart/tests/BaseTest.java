package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	LoginPage loginpage;
	Properties prop;
	AccountsPage accPage;
	RegisterPage repage;
	ResultsPage resultPage;
	ProductInfoPage productInfopage;
	SoftAssert SoftAssert;
	
	@BeforeClass
	public void setUp() {
	df = new DriverFactory();
	prop = df.init_prop();
	driver=df.init_driver(prop);
	loginpage = new LoginPage(driver);
	SoftAssert = new SoftAssert();
	}
	
	
	@AfterTest
	public void tearDown() {
//		driver.quit();
	}
	
}
