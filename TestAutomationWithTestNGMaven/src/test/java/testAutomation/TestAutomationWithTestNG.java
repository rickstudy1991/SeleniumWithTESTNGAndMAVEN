package testAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testAutomationMain.ReadPropertyFile;
import testAutomationMain.WebElementRepository;



public class TestAutomationWithTestNG {
  
	public WebDriver driver;
	  
	static WebElementRepository elementRepo = new WebElementRepository();
	ReadPropertyFile rpf = new ReadPropertyFile();
	
  @BeforeTest
  public void browserLaunch() {
	  
	System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();	
	driver.get(rpf.readFrompropertyFile().getProperty("URL"));
	driver.manage().window().maximize();      
	 
  }
	  
  @Test(priority = 0)
  public void verifyHomepageTitle() {
	   String actualTitle = driver.getTitle();
       String expectedTitle = "Welcome: Mercury Tours";
       
       Assert.assertEquals(actualTitle, expectedTitle);
       				
  }
  
  @Test(priority = 1)
  public void verifyFlightBooking() {
	  
	  	driver.findElement(By.xpath(elementRepo.userName_textbox)).sendKeys(rpf.readFrompropertyFile().getProperty("Username"));
		driver.findElement(By.xpath(elementRepo.password_textbox)).sendKeys(rpf.readFrompropertyFile().getProperty("Password"));
		driver.findElement(By.xpath(elementRepo.submit_button)).click();
		driver.findElement(By.xpath(elementRepo.flight_link)).click();
		driver.findElement(By.xpath(elementRepo.oneway_radiobutton)).click();
		
		////////////////////// Selecting drop down value ////////////////////////////////
		
		Select passengerSelect = new Select(driver.findElement(By.xpath(elementRepo.passenger_dropdown))); 		
		passengerSelect.selectByValue("2");
		
		Select arrivalSelect = new Select(driver.findElement(By.xpath(elementRepo.arrival_dropdown))); 		
		arrivalSelect.selectByValue("London");
		
		driver.findElement(By.xpath(elementRepo.continue_button)).click();
		
		System.out.println("Test Passed");
             
      				
 }
  
  @AfterTest
  public void closeBrowser() {
	   driver.close();
       				
  }
  
  
  
}
