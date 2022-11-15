package com.selenium.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestngFlow {
	
	@BeforeSuite
	public void executeBeforeEverySuite() {

		System.out.println("executed Before Every Suite");
	}
	
	@BeforeTest
	public void executeBeforeEveryTest() {

		System.out.println("executed Before Every Test");
	}

	@BeforeClass
	public void executeBeforeEveryClass() {

		System.out.println("executed Before Every Class");
	}

	@BeforeMethod
	public void executeBeforeEveryMethod() {

		System.out.println("executed Before Every Method");
	}

	@AfterMethod
	public void executeAfterEveryMethod() {
		System.out.println("executed After Every Method");
	}

	@AfterClass
	public void executeAfterEveryClass() {

		System.out.println("executed After Every Class");
	}

	@AfterTest
	public void executeAfterEveryTest() {
		System.out.println("executed After Every Test");
	}
	
	@AfterSuite
	public void executeAfterEverySuite() {
		System.out.println("executed After Every Suite");
	}

}
