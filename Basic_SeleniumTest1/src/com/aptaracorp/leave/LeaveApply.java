package com.aptaracorp.leave;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LeaveApply {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://leave.aptaracorp.com/");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys("A6019");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys("mohanpur@12345");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_LnkLvApply")).click();
		
		//For apply Button
		WebElement apply=driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_Td1']/input[1]"));
		String onclk=apply.getAttribute("onclick");
		
		
       if(onclk.equals("checkDateOnClick()")) {
    	   apply.click();
    	   String str=driver.switchTo().alert().getText();
    	   driver.switchTo().alert().accept();
    	   System.out.println(str);		
		}
       else {
		//From leave date
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ImgBntCalc")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_CalendarExtender1_day_2_2")).click();
		
		//To leave date
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ImgBntCalc1")).click();
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_CalendarExtender2_day_2_4")).click();
		
		//for Resone textbox
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TXTREMARKS")).sendKeys("For Urgent Work");
		
		//For radio Button
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_radioday_0")).click();
		
		//for Leave type
		Select Leave_Type=new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_DDLREASON")));
		Leave_Type.selectByVisibleText("SL  - SICK LEAVE"); 
		
		apply.click();
		System.out.println("You have successfully apply a leave");
			
       }		

	}

}
