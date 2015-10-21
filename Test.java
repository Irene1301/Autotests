package Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.support.ui.Select;
import java.util.regex.Pattern;
import org.openqa.selenium.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Test {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();
	
	  
	public static void setUp() {
	    driver = new FirefoxDriver();
	    baseUrl = "http://192.168.56.1:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	public static void List(){
		//Compare 2 lists elements on Admin site  
		setUp();
		driver.get(baseUrl + "/joomla/administrator");
		driver.findElement(By.id("mod-login-username")).sendKeys("irina");
		driver.findElement(By.id("mod-login-password")).sendKeys("Irene130180");
		driver.findElement(By.xpath("//*[@id='menu']/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id='menu']/li[2]/ul/li[1]/a")).click();
		
		List expected = new ArrayList();
		expected.add("irina");
		expected.add("User");
		Collections.sort(expected);
		System.out.println("List expected:"+ expected);

		List actual = new ArrayList();
		String element1 = driver.findElement(By.xpath("//*[@id='userList']/tbody/tr[1]/td[2]/div[1]/a")).getText();
		String element2 = driver.findElement(By.xpath("//*[@id='userList']/tbody/tr[1]/td[2]/div[1]/a")).getText();
		actual.add(element1);
		actual.add(element2);
		System.out.println("List actual:"+ actual);
		
		boolean result = expected.equals(actual);
		System.out.println(result);
		driver.close();
		}
		

	 public static void Test01()
	 //Verifies there's a link "Getting Started" on home page
	 {
		setUp();
		driver.get(baseUrl + "/joomla/");
		driver.findElement(By.cssSelector("div.tagspopular > ul > li > a")).click();
	    try {
	      assertEquals("Getting Started", driver.findElement(By.cssSelector("h3")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	 }
	 
	 public static void Test02()
	 //Check that text "logging" is not present on the page
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		    try {
		      assertThat("logging", is(not(driver.findElement(By.xpath("//main[@id='content']/div[3]/div[2]/p")).getText())));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	 }
	 
	 public static void Test03()
	 //Check user is able to login with userName +password and gets to page with "Logout" button
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.id("modlgn-username")).clear();
		 driver.findElement(By.id("modlgn-username")).sendKeys("irina");
		 driver.findElement(By.id("modlgn-passwd")).clear();
		 driver.findElement(By.id("modlgn-passwd")).sendKeys("Irene130180");
		 driver.findElement(By.name("Submit")).click();
		    try {
		    	 driver.findElement(By.xpath("//*[@id='form-login-submit']/div/button")).isDisplayed(); 
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("Log out", driver.findElement(By.xpath("//*[@id='form-login-submit']/div/button")).getAttribute("value"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	 }
	 
	 public static void Test04()
	 //Check clicking on "Submit Article" link gets you to a page containing "Article" button
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.id("modlgn-username")).clear();
		 driver.findElement(By.id("modlgn-username")).sendKeys("irina");
		 driver.findElement(By.id("modlgn-passwd")).clear();
		 driver.findElement(By.id("modlgn-passwd")).sendKeys("Irene130180");
		 driver.findElement(By.name("Submit")).click();
		
		 driver.findElement(By.xpath("//*[@id='aside']/div[3]/ul/li[2]/a")).click();
		 try {
		      assertEquals("Article", driver.findElement(By.xpath("//*[@id='editor-xtd-buttons']/a[1]")).getAttribute("title"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	 }
	 
	 public static void Test05()
	 //Verify 'Warning' title and warming message gets displayed at the attempt to login with incorrect userName +password
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.id("modlgn-username")).clear();
		    driver.findElement(By.id("modlgn-username")).sendKeys("ttt");
		    driver.findElement(By.id("modlgn-passwd")).clear();
		    driver.findElement(By.id("modlgn-passwd")).sendKeys("123");
		    driver.findElement(By.name("Submit")).click();
		    try {
		    assertEquals("Warning", driver.findElement(By.xpath("//*[@id='system-message']/div/h4")).getText());
		    assertEquals("Username and password do not match or you do not have an account yet.", driver.findElement(By.cssSelector(".alert-message")).getText());
		    } catch (Error e) {
		    	verificationErrors.append(e.toString());
		    }
		    driver.close();
	 }	
	 
	 public static void Test06()
	 //Verify user is able to login with correct name+ password after 'Warning' message 
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.id("modlgn-username")).clear();
		 driver.findElement(By.id("modlgn-username")).sendKeys("ttt");
		 driver.findElement(By.id("modlgn-passwd")).clear();
		 driver.findElement(By.id("modlgn-passwd")).sendKeys("123");
		 driver.findElement(By.name("Submit")).click();
		    try {
		    assertEquals("Warning", driver.findElement(By.xpath("//*[@id='system-message']/div/h4")).getText());
		    assertEquals("Username and password do not match or you do not have an account yet.", driver.findElement(By.cssSelector(".alert-message")).getText());
		    } catch (Error e) {
		    	verificationErrors.append(e.toString());
		    }
		    driver.findElement(By.id("modlgn-username")).clear();
			driver.findElement(By.id("modlgn-username")).sendKeys("irina");
			driver.findElement(By.id("modlgn-passwd")).clear();
			driver.findElement(By.id("modlgn-passwd")).sendKeys("Irene130180");
			driver.findElement(By.name("Submit")).click();
			 try {
			      assertEquals("Log out", driver.findElement(By.name("Submit")).getAttribute("value"));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		  driver.close();
	 }	
	 
	 public static void Test07()
	 //Verify correct form is displayed after clicking Forgot your password? link  
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.linkText("Forgot your username?")).click();
		    assertEquals("Please enter the email address associated with your User account. Your username will be emailed to the email address on file.", driver.findElement(By.xpath("//*[@id='user-registration']/fieldset/p")).getText());
		    try{
		    driver.findElement(By.className("Submit")).isEnabled();
		    driver.findElement(By.id("jform_email")).isDisplayed();
		    driver.findElement(By.id("jform_email-lbl")).isDisplayed();
		    } catch (Error e) {
			      verificationErrors.append(e.toString());
		    }
		   try {
		    assertEquals("30", driver.findElement(By.id("jform_email")).getAttribute("size"));
		    assertEquals("jform[email]", driver.findElement(By.id("jform_email")).getAttribute("name"));
		    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		    
		    try {
		      assertTrue(driver.findElement(By.id("jform_email-lbl")).getText().matches("^Email Address [\\s\\S]*$"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    driver.close();
	 }
	 
	 public static void Test08()
	//Verify the Title of this page
	 {
		 setUp();
	 driver.get(baseUrl +"/joomla/");
	 String title = driver.getTitle();
	 System.out.println("Page title is: "+title);
	 assertEquals("Home", title);
	 driver.close();
	 }
	
	 public static void Test09()

	 //Verify tooltip   
	 {
		 setUp();
		 driver.get(baseUrl + "/joomla/");
		 driver.findElement(By.linkText("Forgot your password?")).click();
		    assertEquals("Please enter the email address for your account. A verification code will be sent to you. Once you have received the verification code, you will be able to choose a new password for your account.", driver.findElement(By.cssSelector("fieldset > p")).getText());
		    driver.findElement(By.id("jform_email")).isDisplayed();
		    assertEquals("30", driver.findElement(By.id("jform_email")).getAttribute("size"));
		    assertEquals("jform[email]", driver.findElement(By.id("jform_email")).getAttribute("name"));
		    driver.findElement(By.id("jform_email-lbl")).isDisplayed();
		    try {
		      assertTrue(driver.findElement(By.id("jform_email-lbl")).getText().matches("^Email Address [\\s\\S]*$"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    // Verify tooltip hovering over Email Address* but could nto verify text ?
		    try {
		      assertEquals("", driver.findElement(By.cssSelector("#jform_email-lbl")).getAttribute("title"));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	 }
	 
	 
	  
	 public static void main(String[] args) {
		 List(); 
	}

}
