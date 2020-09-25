package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.AddCustomerPage;
import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.pageobjects.SearchCustomerPage;
import com.nopcommerce.testbase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {

	@Test
	public void searchCustomerbyEmail() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByEmail_004 *************");
		
		driver.get(configProp.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		
		//Go to search page
		AddCustomerPage addCust=new AddCustomerPage(driver);
		
		addCust.clickOnCustomersMenu();
		addCust.clickOnCustomersMenuItem();
		
		//Email ID
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setEmail("james_pan@nopCommerce.com");
		searchcust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchcust.searchCustomerByEmail("james_pan@nopCommerce.com");
		if(status==true)
		{
			logger.info("********* Search customer by emailID is passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("********* Search customer by email is failed*************");
			captureScreen(driver,"searchCustomerbyEmail");
			Assert.assertTrue(false);
		}
		logger.info("********* End of TC_SearchCustomerByEmail_004 *************");
	}
	
}
