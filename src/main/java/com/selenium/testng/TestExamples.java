package com.selenium.testng;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//Run All
public class TestExamples {
	
//	@BeforeGroups("smoke")
//	public void executeBeforeEveryGroups() {
//
//		System.out.println("executed Before  Groups");
//	}
//	
//	@BeforeSuite
//	public void executeBeforeEverySuite() {
//
//		System.out.println("executed Before Every Suite");
//	}
//	
//	@BeforeTest
//	public void executeBeforeEveryTest() {
//
//		System.out.println("executed Before Every Test");
//	}
//
//	@BeforeClass
//	public void executeBeforeEveryClass() {
//
//		System.out.println("executed Before Every Class");
//	}
//
//	@BeforeMethod
//	public void executeBeforeEveryMethod() {
//
//		System.out.println("executed Before Every Method");
//	}
//	@Test(groups = {"smoke"})
//	public void executeBeforeTest() {
//
//		System.out.println("executed Test");
//	}
//
//	@AfterMethod
//	public void executeAfterEveryMethod() {
//		System.out.println("executed After Every Method");
//	}
//
//	@AfterClass
//	public void executeAfterEveryClass() {
//
//		System.out.println("executed After Every Class");
//	}
//
//	@AfterTest
//	public void executeAfterEveryTest() {
//		System.out.println("executed After Every Test");
//	}
//	
//	@AfterSuite
//	public void executeAfterEverySuite() {
//		System.out.println("executed After Every Suite");
//	}
//	@AfterGroups("smoke")
//	public void executeAfterEveryGroups() {
//		System.out.println("executed After  Groups");
//	}

	@Test(priority = 1, description = "Loggin to the Portal")
	public void LoginTest() {
		System.out.println("Logged In Successful");
	}

	@Test(priority = 2, description = "HomePage Launching")
	public void homePage() {
		System.out.println("HomePage Launched Successfully");
		String expected = "Selenium-Java";
		String actual = "Selenium-Java";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 3, description = "MyAccount Launching")
	public void MyAccount() {
		System.out.println("HomePage Launched Successfully");
		String expected = "TestNG";
		String actual = "TestNG";
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(actual, expected);

	}

	@Test(priority = 5)
	public void skipTest1() {
		System.out.println("Skipping this test1 as it is not complete");
	}

	@Test(priority = 4)
	public void skipTest2()// if skipTest1() test annotation attribute is enabled = false then skipTest2()
							// will not consider or not execute
	{
		System.out.println("Skipping this test2 as it is not complete");
		throw new SkipException("Skipping this test2");
	}

	@Test(priority = 6, description = "Logout to the Portal")
	public void LogOutTest() {
		System.out.println("Logged Out Successful");
	}

}
