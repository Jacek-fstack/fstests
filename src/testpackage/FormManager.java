package testpackage;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/* May be to general to do this in a single class, but for now, everything that is used for creating forms etc is here */
public class FormManager {

	public static void createBlankForm(String baseUrl, WebDriver driver) throws Exception {
		
		initiateFormCreation(baseUrl, driver);
		driver.findElement(By.partialLinkText("Start with a blank")).click();
		
	}
	
	// The Template form should be as visible in the FS app, didn't check all of them, but should work fine
	public static void createTemplateForm(String baseUrl, WebDriver driver, String templateName) throws Exception {
		
		initiateFormCreation(baseUrl, driver);
		driver.findElement(By.partialLinkText("Start with a Template")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.='"+ templateName +"']")).click();
		Thread.sleep(500);
		driver.findElement(By.partialLinkText("Next Step")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[starts-with(@alt, 'Formstack Light ')]")).click();
		Thread.sleep(500);
		driver.findElement(By.partialLinkText("Finish")).click();
	}
	
	public static void createBlankWorkflow(String baseUrl, WebDriver driver) throws Exception {
		
		initiateFormCreation(baseUrl, driver);
		driver.findElement(By.xpath("//*[contains(text(),'Workflow Form')]")).click();
		driver.findElement(By.partialLinkText("Start with a blank")).click();
		
		
	}
	
	// The Template form should be as visible in the FS app, didn't check all of them, but should work fine
	public static void createTemplateWorkflow(String baseUrl, WebDriver driver, String templateName) throws Exception {
		
		initiateFormCreation(baseUrl, driver);
		driver.findElement(By.xpath("//*[contains(text(),'Workflow Form')]")).click();
		driver.findElement(By.partialLinkText("Start with a Template")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.='"+ templateName +"']")).click();
		Thread.sleep(500);
		driver.findElement(By.partialLinkText("Next Step")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[starts-with(@alt, 'Formstack Light ')]")).click();
		Thread.sleep(500);
		driver.findElement(By.partialLinkText("Finish")).click();
		
	}
	
	// Set of things that are filled anyway for every form or workflow (form name, etc)
	static void initiateFormCreation(String baseUrl, WebDriver driver) throws Exception{
		
		RandomString rand = new RandomString(8, ThreadLocalRandom.current());
		String random = rand.nextString();
		
		NavigationHelper.navigateToFormsTab(baseUrl, driver);

		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='js--forms-list-content-anchor']/div/folder-details/folder-overview/div[2]/div[2]/fs-btn[2]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("formName")).sendKeys("Some Name "+ random);
		Thread.sleep(2000);
	}
	
	
}
