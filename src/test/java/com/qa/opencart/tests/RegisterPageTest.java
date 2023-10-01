package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regSetUP() {
		repage=loginpage.goToRegisterPage();
	}

	public String getRandomNumber() {
		Random randomGen = new Random();
		String email = "testautomationShalu" + randomGen.nextInt() +"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][]= ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegisterData" )
	public void userRegiistractionTest(String firstname, 
			String lastname, String telephone, 
			String password, String subscribe) {
			Assert.assertTrue(repage.accountRegistration(firstname, lastname, getRandomNumber(), telephone, password, subscribe));
	}
 
/*	@Test
	public void userRegiistractionTest() {
		Assert.assertTrue(repage.accountRegistration("Deenu", "SS", "deenuss1989@gmail.com", "9746419193", "deenu@1234", "yes"));
	}*/
}
