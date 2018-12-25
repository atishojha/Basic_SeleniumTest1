package com.aptaracorp.WorkOnFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Notepad_TestFile {

	public static void main(String[] args) {
		try {
            FileReader reader = new FileReader("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\src\\com\\aptaracorp\\TestDataSheet\\Login_Info.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
           
            while ((line = bufferedReader.readLine()) != null) {
                String[]data=line.split(" and ");
                
                String[]user_id=data[0].split("=");
                String emp_id=user_id[1];
                
                String[]password=data[1].split("=");
                String pwd=password[1];
                
            	WebDriver driver=new ChromeDriver();
        		driver.get("http://leave.aptaracorp.com/");
        		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys(emp_id);
        		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys(pwd);
        		try {
        			driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
        			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        			driver.switchTo().alert().accept();
        		}catch (NoAlertPresentException e) { 
        				System.out.println(e);
        				}
        		String BrowserTitle=driver.getTitle();
        		if(BrowserTitle.equals("Home Page")) {
        		System.out.println("Login Sucessfully");
        		driver.close();
        		}
        		else {
        		System.out.println("Login Unsucessfull");
        		driver.close();	            
            } 
            
            reader.close();
        } 
	}catch (IOException e) {
        e.printStackTrace();
    }


	}

}
