package com.nopcommerce.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageobjects.LoginPage;
import com.nopcommerce.testbase.BaseClass;

public class TC_LoginTest_001 extends BaseClass{
	
	
	
	@Test
	void loginTest() throws IOException {
		
		logger.info("****   Starting TC_LoginTest_001    *****");
		
		driver.get(configProp.getProperty("baseURL"));
		
		
		LoginPage lp = new LoginPage(driver);
		
		logger.info("****   Providing login details  *****");
		lp.setUserName(configProp.getProperty("useremail"));
		lp.setPassword(configProp.getProperty("password"));
		lp.clickLogin();
		
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
	
		if(act_title.equals(exp_title)) {
			
			logger.info("****   Test case passed     *****");
			Assert.assertTrue(true);
		}
		else {
			
			logger.error("****   Test case failed     *****");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		logger.info("****   Completed TC_LoginTest_001     *****");
	}
	


}
