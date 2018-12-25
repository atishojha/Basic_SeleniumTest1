package com.aptaracorp.WorkOnFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



public class Property_TestFile {
	public static WebDriver driver;
	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fin=new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\src\\com\\aptaracorp\\TestDataSheet\\Login_Info.properties");
		prop.load(fin);
		String Browser=prop.getProperty("browser");
		if(Browser.equals("chrome")) { 
		 driver=new ChromeDriver();
		}else if(Browser.equals("firefox")) {
		 driver=new FirefoxDriver();	 
		 }else
		driver=new InternetExplorerDriver(); 
			 
		
		driver.get(prop.getProperty("url"));
		
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys(prop.getProperty("emp_id"));
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.close();
	}

}
