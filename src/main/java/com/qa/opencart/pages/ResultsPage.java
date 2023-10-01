package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchHeader = By.cssSelector("div#content h1");
	private By productResult = By.cssSelector("div.caption a");
	
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int productListCount() {
		int totalCount = eleUtil.waitForElementsVisible(productResult, 10).size();
		System.out.println("Total Count of product is " + totalCount);
		return totalCount;
	}
	public ProductInfoPage selectProduct(String mainProduct) {
		System.out.println("Product Name is " + mainProduct);
		List<WebElement> searchList = eleUtil.waitForElementsVisible(productResult, 10);
		for(WebElement ele: searchList) {
			String text = ele.getText();
			if(text.equals(mainProduct)) {
				ele.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	
}
