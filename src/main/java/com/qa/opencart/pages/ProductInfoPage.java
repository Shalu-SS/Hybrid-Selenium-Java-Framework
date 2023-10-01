package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private By productHeaderName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCarBtn = By.id("button-cart");
	private Map<String, String> productMap;

	private ElementUtil eleUtil;
	
	ProductInfoPage(WebDriver driver){
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeaderName() {
		return eleUtil.doGetText(productHeaderName);
	}
	
	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImages, 10).size();
	}
	public Map<String, String> getProductInfo() {
		productMap = new HashMap<String, String>();
//		productMap = new TreeMap<String, String>(); //Maintain the Sorted Order
//		productMap = new LinkedHashMap<String, String>();//Maintain the given Order
		productMap.put("name", getProductHeaderName());
		productMap.put("totalImages", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	private void getProductMetaData() {
	List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
	for(WebElement ele:metaDataList) {
		String text=ele.getText();
		String meta[]=text.split(":");
		String key = meta[0].trim();
		String value = meta[1].trim();
		productMap.put(key, value);
	}
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPricrList = eleUtil.getElements(productPriceData);
		String price = metaPricrList.get(0).getText().trim();
		String exTaxprice = metaPricrList.get(1).getText().trim();
		productMap.put("price", price);
		productMap.put("ExTaxprice", exTaxprice);
	}
}
