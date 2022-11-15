package com.selenium.testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion {
	
	@Test
	void testOne() {
		Assert.assertTrue(false);
		System.out.println("This is Test1 Executed ");
	}

	@Test
	void testTwo() {
		System.out.println("This is Test2 Executed ");
	}

	
	@Test
	void testThree() {

		SoftAssert softAssert =new SoftAssert();
		softAssert.assertTrue(false);
		System.out.println("This is Test3 Executed ");
		softAssert.assertAll();
	}

	@Test
	void testFour() {
		System.out.println("This is Test4 Executed ");
	}

}
