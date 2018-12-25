package com.aptaracorp.WorkOnFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Excel_TestFile {

public static void main(String[] args) throws IOException {
		
	File input_file=new File("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\src\\com\\aptaracorp\\TestDataSheet\\Login_Info.xlsx");
	FileInputStream fin=new FileInputStream(input_file);
	XSSFWorkbook workbook=new XSSFWorkbook(fin);
    XSSFSheet sheet=workbook.getSheetAt(0);
	
    int TotalCol=sheet.getRow(0).getLastCellNum();
	int TotalRow=sheet.getLastRowNum();

	for(int i=1;i<=TotalRow;i++) {

		String emp_id=sheet.getRow(i).getCell(0).getStringCellValue();
		String emp_pwd=sheet.getRow(i).getCell(1).getStringCellValue();
		WebDriver driver=new ChromeDriver();
		driver.get("http://leave.aptaracorp.com/");
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtUserName")).sendKeys(emp_id);
		driver.findElement(By.id("ctl00_ContentPlaceHolder1_TxtPassword")).sendKeys(emp_pwd);	
		try {
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_BtnLogin")).click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.switchTo().alert().accept();
		}catch (NoAlertPresentException e) { 
				System.out.println(e);
				}
		
		String BrowserTitle=driver.getTitle();
		if(BrowserTitle.equals("Home Page")){
		File output_file=new File("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\src\\com\\aptaracorp\\TestDataSheet\\Login_Info.xlsx");
		FileOutputStream fout=new FileOutputStream(output_file);
		sheet.getRow(i).createCell(2).setCellValue("Pass");
		workbook.write(fout);
		fout.close();
		System.out.println("Login Sucessfully");
		driver.close();
		} 
		else {
				File output_file=new File("C:\\Users\\Dell\\eclipse-workspace\\Basic_SeleniumTest1\\src\\com\\aptaracorp\\TestDataSheet\\Login_Info.xlsx");
				FileOutputStream fout=new FileOutputStream(output_file);
				sheet.getRow(i).createCell(2).setCellValue("Fail");
				workbook.write(fout);
				fout.close();
				System.out.println("Login Unsucessfully");
				driver.close();	
				} 
		}   
	}

}
