package com.nopcommerce.testbase;

import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

   

public class BaseClass {
	
	public WebDriver driver;
	public Properties configProp;
	
	
	public Logger logger=LogManager.getLogger(this.getClass()); // Log4j2
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {

		if(br.equals("chrome")) {
			
			//System.setProperty("webdriver.chrome.silentOutput","true");
			WebDriverManager.chromedriver().setup(); 
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(br.equals("edge")) {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		configProp = new Properties();
		FileInputStream configFile = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");

		configProp.load(configFile);


	}

	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		
		//File source = ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String getRandomString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return (randomString);
	}
	
	public int getRandomNum() {
		String randomNum = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(randomNum));
	}

}
