package testpackage;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/* Class should ideally handle all things connected to creating users, adding them, deleting, creating accounts etc etc */

public class AccountManager {
	
	// Create account, feel free to change these values
	public static void createAccount(String baseUrl, WebDriver driver) throws InterruptedException{
		
	
		RandomString rand = new RandomString(8, ThreadLocalRandom.current());
		String random = rand.nextString();
	
		driver.manage().window().maximize();
		driver.get(baseUrl);
		
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.className("registerLink")).click();
		
		driver.findElement(By.name("firstName")).sendKeys("Jacek");
		driver.findElement(By.name("lastName")).sendKeys("Adamczyk");
		
		//Adds a random part of a string, so it won't repeat e-mail while signing up. There is a chance that it will, but REALLY small
		driver.findElement(By.name("email")).sendKeys("jacektestingacc+"+ random +"@gmail.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.name("company")).sendKeys("Dupa");
		
		//Phone format suited for US
		driver.findElement(By.name("phone")).sendKeys("(201) 555-2312");
		driver.findElement(By.xpath("//*[text()=' I understand and agree to the ']")).click();
		driver.findElement(By.className("fs--mt25")).click();
	
		//Waits 10 seconds to let e-mail arrive
		TimeUnit.SECONDS.sleep(10);
		
		try {
			MailHelper.confirmAccoubtWithEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(MailHelper.getString());
		

		
		industryCookie(driver);
		
		
	}
	
	// Adds a user to the account, uses random string, so it won't repeat e-mail while signing up. There is a chance that it will, but REALLY small
	public static void addUser(String baseUrl, WebDriver driver){
		
		RandomString rand = new RandomString(8, ThreadLocalRandom.current());
		String random = rand.nextString();
		
		NavigationHelper.navigateToUsersList(baseUrl, driver);
		driver.findElement(By.xpath(".//*[@id='user-management']/div[2]")).click();
		driver.findElement(By.id("new-user-firstname")).sendKeys("John");
		driver.findElement(By.id("new-user-lastname")).sendKeys("Doe");
		driver.findElement(By.xpath(".//*[@id='user-management']/table/tbody/tr[2]/td[2]/input")).sendKeys("jacektestingacc+"+ random +"@gmail.com");
		driver.findElement(By.linkText("Save")).click();
	}
	
	public static void logInWithExistingUser(String baseUrl, WebDriver driver, String email, String password){
		
		driver.get(baseUrl);
		industryCookie(driver);
		
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("submit")).click();
		
	}
	
	//This method is added, to temporarily add the industry cookie, so the annoying popup does not show up - until we figure out a better way to handle this
	static void industryCookie(WebDriver driver){
		
		Cookie ck = new Cookie("industryPopUp", "Education");
		driver.manage().addCookie(ck);
	}

}
