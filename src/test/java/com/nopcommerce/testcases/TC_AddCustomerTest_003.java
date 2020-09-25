package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.AddCustomerPage;
import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.testbase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_AddCustomerTest_003 *************");

		driver.get(configProp.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);

		logger.info("*********Adding new customer *************");

		AddCustomerPage addcust=new AddCustomerPage(driver);

		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
		Thread.sleep(2000);

		logger.info("***************  Providing customer details  *********** ");

		String email=getRandomString()+"@gmail.com";

		addcust.setEmail(email);
		addcust.setPassword("Selenium@123");
		addcust.setFirstName("Mahesh");
		addcust.setLastName("Babu");
		addcust.setGender("Male");
		addcust.setDob("2/25/1988"); // Format: MM/DD/YYYY
		addcust.setCompanyName("ABC");
		addcust.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("This is for testing.........");
		addcust.clickOnSave();
		Thread.sleep(3000);
		
		
		

		// validation
		if (addcust.verifyConfirmationMsg()) {
			logger.info("***************  Customer added succesfully *********** ");
			Assert.assertTrue(true);

		} else {
			logger.error("*************** Customer Not added succesfully *********** ");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
		logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
	}

}
