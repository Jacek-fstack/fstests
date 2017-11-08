package testpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/* Class handles Deactivating or deleting paid accounts */
public class DeactivateOrDeleteAccount {

	// deactivates the account 
	public static void deactivateAccount(String baseUrl, WebDriver driver) throws InterruptedException{
	
		NavigationHelper.navigateToPlanList(baseUrl, driver);
	
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/span/fs-container/div/div[1]/a/div[1]/p")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[2]/div[1]/fs-btn/a/div[1]/p")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[3]/fs-btn[2]/a/div[1]/p")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/div[1]/div/div[1]/ul/li[4]/fs-checkbox/fs-checkbox-input/fs-checkbox-label/label")).click();
	
		
		Select reason = new Select(driver.findElement(By.id("reason")));
		reason.selectByVisibleText("None of the above");

		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/div[4]/fs-btn[2]/a/div[1]/p")).click();	
		Thread.sleep(1500);
		driver.findElement(By.id("comments")).sendKeys("1234");
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[3]/fs-btn[2]/a/div[1]/p")).click();
		
		
	}
	
	// deletes the account
	public static void deleteAccount(String baseUrl, WebDriver driver) throws InterruptedException{
		
		NavigationHelper.navigateToPlanList(baseUrl, driver);
	
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/span/fs-container/div/div[1]/a/div[1]/p")).click();
		Thread.sleep(1500);

		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[2]/div[2]/fs-btn/a/div[1]/p")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[3]/fs-btn[2]/a/div[1]/p")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/div[1]/div/div[1]/ul/li[4]/fs-checkbox/fs-checkbox-input/fs-checkbox-label/label")).click();
	
		
		Select reason = new Select(driver.findElement(By.id("reason")));
		reason.selectByVisibleText("None of the above");

		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[2]/div[4]/fs-btn[2]/a/div[1]/p")).click();	
		Thread.sleep(1500);
		driver.findElement(By.id("comments")).sendKeys("1234");
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[3]/fs-btn[2]/a/div[1]/p")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("confirm")).sendKeys("Delete my account");
		driver.findElement(By.xpath(".//*[@id='ngdialog1']/div[2]/div[1]/div[3]/fs-btn[2]/a/div[1]/p")).click();
		
	}
}
