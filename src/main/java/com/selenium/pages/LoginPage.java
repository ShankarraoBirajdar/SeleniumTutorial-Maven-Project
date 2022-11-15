package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	By userName = By.id("user-name");
	By password= By.id("password");
	By loginButton = By.xpath("//input[@id='login-button']");

	WebDriver driver =null;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void login(String username, String Password) {
		
		driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(loginButton).click();
		
	}

}
