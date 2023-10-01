package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	//private Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password"); 
	private By loginBtn = By.xpath("//input[@type='submit']"); 
	private By forgotPwdLink = By.linkText("Forgotten Password11"); 
	private By registerLink = By.linkText("Register"); 
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	@Step("GetPageTitle")
	public String getLoginPageTitle() {
		return eleutil.doGetPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	@Step("getLoginUrl")
	public String getLoginUrl() {
		return eleutil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION, 5);
	}
	public boolean isForgotPwdLinkExits() {
		return eleutil.doIsdisplyed(forgotPwdLink);
	}
	public AccountsPage doLogin(String userName,String pwd) {
		eleutil.doSendKeys(emailId, userName);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	public RegisterPage goToRegisterPage() {
		eleutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
