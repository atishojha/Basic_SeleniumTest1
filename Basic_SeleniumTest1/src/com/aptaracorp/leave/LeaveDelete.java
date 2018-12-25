package com.aptaracorp.leave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeaveDelete {

	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the Date in dd/MM/yyyy formate ");
		String DateFrm = scn.next();
		
		System.out.println("Enter the Second date");
		String DateT=scn.next();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date DateFrom=null;
		Date DateTo=null;
		try {
		    DateFrom = dateFormat.parse(DateFrm);
		    DateTo=dateFormat.parse(DateT);
		    
		} catch (ParseException e) { }  

		WebDriver driver=new ChromeDriver();
		driver.get("http://leave.aptaracorp.com/");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys("A6019");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys("mohanpur@12345");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_LnkLvApply")).click();
		
		WebElement Table=driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody"));
		List<WebElement>Rows=Table.findElements(By.tagName("tr"));
		
		int DelCount=0;
		for(int i=2;i<=(Rows.size());i++)
		{
			driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody/tr["+i+"]"));
			WebElement dele=driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody/tr["+i+"]/td["+1+"]"));
			String del=dele.getText();
			
			if(del.equals("Delete")) {
				DelCount++;
			}
		}
	
		if(DelCount>0) {
			int itr=0;
		for(int i=2;i<=DelCount+1;i++)
		  {
				List<WebElement>FrmDate=driver.findElements(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody/tr["+i+"]/td["+4+"]"));
				List<WebElement>TDate=driver.findElements(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody/tr["+i+"]/td["+5+"]"));
				
				WebElement fdate=FrmDate.get(0);
				String fdate1=fdate.getText();			
				WebElement tdate=TDate.get(0);
				String tdate1=tdate.getText();
				
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
				Date FromDate=null;
				Date ToDate=null;
				try {
				    FromDate = dateFormat1.parse(fdate1);
				    ToDate=dateFormat1.parse(tdate1);				    
				} catch (ParseException e) { }	
				
				if((DateFrom.compareTo(FromDate)>=0)&&(DateTo.compareTo(ToDate)<=0))
				{
					driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdrequest']/tbody/tr["+i+"]/td["+1+"]")).click();
					driver.switchTo().alert().accept();
					System.out.println("Leave Successfully deleted");
					break;
				}
				else if((DateFrom.compareTo(FromDate)<0)&&((DateTo.compareTo(FromDate)>=0)&&(DateTo.compareTo(ToDate)<=0)))
				{
					System.out.println("You have enter wrong From_Leave_Date");
				}
				else if(((DateFrom.compareTo(FromDate)>=0)&&(DateFrom.compareTo(ToDate)<=0))&&(DateTo.compareTo(ToDate)>0))
				{
				 System.out.println("You have enter wrong TO_Leave_Date");
				}
				else
				{
					itr++;
				}
			}
		if(itr==DelCount)
		{
			System.out.println("There is no apply leave in between your given date");
		}
		}else
			{
				System.out.println("In Your Leave Dashboard, there is not present any apply leave");
			}

	}

}
