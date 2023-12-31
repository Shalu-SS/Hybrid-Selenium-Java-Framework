package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By sections = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.xpath("//input[@name='search']");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		return eleUtil.doGetPageTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageUrl() {
		return eleUtil.waitForURLContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isLogoutLinkExist() {
		
		return eleUtil.doIsdisplyed(logoutLink);
	}

	public boolean logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
			return true;
		}
		else {
			return false;
		}
	}
	public List<String> getAccPageSections() {
		List<WebElement> sectionList = eleUtil.waitForElementsVisible(sections, 10);
		List<String> secValList = new ArrayList<>();
		for(WebElement ele:sectionList) {
			String val = ele.getText();
			secValList.add(val);
		}
		return secValList;
	}
	public boolean searchExist() {
		return eleUtil.doIsdisplyed(search);
	}
	public ResultsPage doSearch(String productName) {
		if(searchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
		}
		return new ResultsPage(driver);
	}
}
