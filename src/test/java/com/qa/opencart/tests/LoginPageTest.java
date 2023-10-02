package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Epic("EPIC - 100 : Design Login Page for Open cart Application...")
//@Story("US - 101: Login Page Features")
public class LoginPageTest extends BaseTest{

//	@Description("login Page Title Test")
//	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("Login Page title :" +title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}


//	@Description("login Page URL Test")
//	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageURLTest() {
		String url = loginpage.getLoginUrl();
		System.out.println("Login Page URL :" +url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
//	@Description("login Page Forgot Pwd Link Exits Test")
//	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void ForgotPwdTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExits());
	}
	
//	@Description("Positive test case for login with credentials...")
//	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Positive test case for login with credentials...")
	public void loginTest() {
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
