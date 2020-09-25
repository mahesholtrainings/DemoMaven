package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.AddCustomerPage;
import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.pageobjects.SearchCustomerPage;
import com.nopcommerce.testbase.BaseClass;

public class TC_SearchCustomerByName_005 extends BaseClass {

	@Test
	public void searchCustomerbyName() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByName_005 *************");
		
		driver.get(configProp.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		
		//Go to search page
		AddCustomerPage addCust=new AddCustomerPage(driver);
		
		addCust.clickOnCustomersMenu();
		addCust.clickOnCustomersMenuItem();
		
		//Name
		SearchCustomerPage searchCust=new SearchCustomerPage(driver);
		searchCust.setFirstName("James");
		searchCust.setLastName("Pan");
		searchCust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchCust.searchCustomerByName("James Pan");
		if(status==true)
		{
			logger.info("********* Search customer by name is passed *************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("********* Search customer by name is failed*************");
			captureScreen(driver,"searchCustomerbyName");
			Assert.assertTrue(false);
		}
		logger.info("********* End of TC_SearchCustomerByName_005 *************");
	}
	
}
