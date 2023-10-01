package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accPage=loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	/*
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
	public void productHeaderTest(String product, String mainProduct) {
		resultPage = accPage.doSearch(product);
		productInfopage = resultPage.selectProduct(mainProduct);
		Assert.assertEquals(productInfopage.getProductHeaderName(), mainProduct);
	}
	*/
	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch("Macbook");
		productInfopage = resultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfopage.getProductHeaderName(), "MacBook Pro");
		
	}
	
	
	@DataProvider
	public Object[][] productData() {
	return new Object[][] {
		{"Macbook", "MacBook Pro", Constants.MACBOOk_IMAGES_COUNT},
		{"Macbook", "MacBook Air", Constants.MACBOOk_IMAGES_COUNT},
		{"iMac", "iMac", Constants.IMAC_IMAGES_COUNT}
	};
}

	@Test(dataProvider = "productData")
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		resultPage = accPage.doSearch(productName);
		productInfopage = resultPage.selectProduct(mainProductName);
		int totalImages = productInfopage.getProductImageCount();
		System.out.println("Total No. of Images " + mainProductName +": "+imagesCount);
		Assert.assertEquals(totalImages, imagesCount);
	}

	@Test
	public void productDataTest() {
		resultPage = accPage.doSearch("Macbook");
		productInfopage = resultPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfopage.getProductInfo();
		actProductInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
		SoftAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		SoftAssert.assertEquals(actProductInfoMap.get("name"),"MacBook Pro");
		SoftAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
		SoftAssert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
		SoftAssert.assertAll();
	}
}
