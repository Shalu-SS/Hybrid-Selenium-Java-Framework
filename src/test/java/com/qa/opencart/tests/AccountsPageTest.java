package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accPageSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest() {
		 String actTitle = accPage.getAccountsPageTitle();
		 System.out.println("Acc page Title: " + actTitle);
		 Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageURL() {
		String actUrl = accPage.getAccountsPageUrl();
		System.out.println("Acc page URL" + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> accSectionList = accPage.getAccPageSections();
		System.out.println(accSectionList);
		Assert.assertEquals(accSectionList, Constants.ACCOUNTS_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "productData")
	public void searchTest(String ProductName) {
		resultPage = accPage.doSearch(ProductName);
		Assert.assertTrue(resultPage.productListCount()>0);
	}
		@DataProvider
		public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultPage = accPage.doSearch(productName);
		productInfopage= resultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfopage.getProductHeaderName(), mainProductName);
	}
}
