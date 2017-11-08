package testpackage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/* Class handles migrations aka plan change for paying client 
 * Keep in mind that Coupons may not work properly yet*/
public class MigratePlan {

	public static void migrateToPlan(String baseUrl, WebDriver driver, String toPlan) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NavigationHelper.navigateToPlanList(baseUrl, driver);
		navigateToPlanSubscriptionPage(driver, toPlan);
		
		driver.findElement(By.xpath(".//*[@id='subscribeForm']/div[3]/div[4]/div[2]/fs-btn-submit/input")).click();
		
	}
	
	// As above, but handles the coupon if it's passed
	public static void migrateToPlan(String baseUrl, WebDriver driver, String toPlan, String coupon) {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NavigationHelper.navigateToPlanList(baseUrl, driver);
		navigateToPlanSubscriptionPage(driver, toPlan);
		driver.findElement(By.id("promo")).sendKeys(coupon);
		driver.findElement(By.xpath(".//*[@id='subscribeForm']/div[3]/div[4]/div[2]/fs-btn-submit/input")).click();
		
	}
	
	// Navigates to plan page
	static void navigateToPlanSubscriptionPage(WebDriver driver, String toPlan){
		
		String buttonAddress = "";
		switch(toPlan) {
		case "silver":
			revealLowerPlans(driver);
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[1]/fs-btn/a/div[1]/p";
			break;
		case "gold":
			revealLowerPlans(driver);
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[2]/fs-btn/a/div[1]/p";
			break;
		case "platinum":
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[3]/fs-btn/a/div[1]/p";
			break;
		case "diamond":
			revealHigherPlans(driver);
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[4]/fs-btn/a/div[1]/p";
			break;
		case "enterprise":
			revealHigherPlans(driver);
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[5]/fs-btn/a/div[1]/p";
			break;
		default:
			break;
		}
		driver.findElement(By.xpath(buttonAddress)).click();
	}
	
	// Reveals diamond and platinum plan since they're not visible straight away
	static void revealHigherPlans (WebDriver driver){
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[2]/a/div[1]/p")).click();
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[2]/a/div[1]/p")).click();
		
		//Has to wait for plans to scroll right
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	// Reveals lower plans, used by default on gold and silver, to make sure they are visible, if for example previous plan was entrerprise
	static void revealLowerPlans (WebDriver driver){
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[1]/a/div[1]/p")).click();
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[1]/a/div[1]/p")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Switches to yearly billing, every time when migrating from any monthly plan to any annual plan
	public static void switchToYearlyBilling(String baseUrl, WebDriver driver) {
		NavigationHelper.navigateToPlanList(baseUrl, driver);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[1]/div[2]/table/tbody/tr/td[2]/fs-btn/a/div[1]/p")).click();
		driver.findElement(By.xpath(".//*[@id='billingForm']/div[1]/div[1]/div[3]/fs-btn/a/div[1]/p")).click();
		driver.findElement(By.xpath(".//*[@id='billingForm']/p[2]/input")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
