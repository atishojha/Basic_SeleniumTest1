package com.aptaracorp.others;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureScreenshot {
	 static WebDriver driver;

	public static void main(String[] args) throws IOException  {
        driver=new ChromeDriver();
		driver.get("http://leave.aptaracorp.com/");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys("A6019");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys("mohanpur@12345");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		takeScreenshot("LeaveHomePage");
		driver.close();

	} 
	public static void takeScreenshot(String filename) throws IOException {
		// Take screenshot and store as a file format             
		 File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(src,new File("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\Output\\CaptureScreenshot\\"+filename+".jpg"));
	}

}
