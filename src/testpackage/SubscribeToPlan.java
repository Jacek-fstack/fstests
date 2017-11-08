package testpackage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/* Subsrcibes free account to one of the  plans 
 * Keep in mind that Coupons may not work properly yet*/

public class SubscribeToPlan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// helping method, fills out fields universal to every nation
	static void fillOutBillingForm(String baseUrl, WebDriver driver) {
		 
		driver.findElement(By.id("cardnumber")).sendKeys("4111111111111111");
		Select selectMonth = new Select(driver.findElement(By.id("cardexpmonth")));
		selectMonth.selectByVisibleText("Feb");
		
		Select selectYear = new Select(driver.findElement(By.id("cardexpyear")));
		selectYear.selectByVisibleText("2022");
		
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Jacek Adamczyk");
		driver.findElement(By.id("cvv")).sendKeys("666");
		driver.findElement(By.id("address1")).sendKeys("Dobra 1a/c/13");
		driver.findElement(By.id("city")).sendKeys("Gdańsk");
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys("512338368");

	}
	
	// as above, but adds coupon if it's passed as a 3rd parameter
	static void fillOutBillingForm(String baseUrl, WebDriver driver, String coupon) {
		fillOutBillingForm (baseUrl, driver);
		driver.findElement(By.id("promo")).sendKeys(coupon);
	}
	
	
	//  subscribe to plan with US parameters
	public static void subscribeToPlanUS(String baseUrl, WebDriver driver, String plan) {
		
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "US";
		fillOutBillingForm(baseUrl, driver);
		
		finishSubscribingByNation(driver, xpathToButton, country);
		
	}
	
	// as above, but adds coupon if it's passed as a 3rd parameter
	public static void subscribeToPlanUS(String baseUrl, WebDriver driver, String plan, String coupon) {
		
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "US";
		fillOutBillingForm(baseUrl, driver, coupon);
		
		finishSubscribingByNation(driver, xpathToButton, country);
		
	}
	
	// Subscribe to a plan with fields for countries other than US
	public static void subscribeToPlanPL(String baseUrl, WebDriver driver, String plan) {
		
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "PL";
		fillOutBillingForm(baseUrl, driver);
		
		finishSubscribingByNation(driver, xpathToButton, country);
		
	}
	
	// as above, but adds coupon if it's passed as a 3rd parameter
	public static void subscribeToPlanPL(String baseUrl, WebDriver driver, String plan, String coupon) {
		
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "PL";
		fillOutBillingForm(baseUrl, driver, coupon);
		
		finishSubscribingByNation(driver, xpathToButton, country);
		
	}
	
	// handles the link to the specific plan page
	static void subscribeToPlanOtherThanGold (String baseUrl, WebDriver driver, String plan){
		
		NavigationHelper.navigateToPlanList(baseUrl, driver);
		
		String buttonAddress = "";
		switch(plan){
		case "silver":
			buttonAddress = ".//*[@id='sourceDetails']/div[2]/div[2]/div[2]/div/div[1]/fs-btn/a/div[1]/p";
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

	
	// Subscribe to a yearly plan for US address
	public static void subscribeToYearlyPlanUS (String baseUrl, WebDriver driver, String plan) {
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "US";
		driver.findElement(By.id("periodicity-yearly")).click();
		fillOutBillingForm(baseUrl, driver);
		
		
		finishSubscribingByNation(driver, xpathToButton, country);
	}
	
	// as above, but adds coupon if it's passed as a 3rd parameter
	public static void subscribeToYearlyPlanUS (String baseUrl, WebDriver driver, String plan, String coupon) {
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "US";
		driver.findElement(By.id("periodicity-yearly")).click();
		fillOutBillingForm(baseUrl, driver, coupon);
		
		
		finishSubscribingByNation(driver, xpathToButton, country);
	}
	
	// Subscribe to a yearly  plan with fields for countries other than US
	public static void subscribeToYearlyPlanPL (String baseUrl, WebDriver driver, String plan) {
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "PL";
		driver.findElement(By.id("periodicity-yearly")).click();
		fillOutBillingForm(baseUrl, driver);
		
		
		finishSubscribingByNation(driver, xpathToButton, country);
	}
	
	// as above, but adds coupon if it's passed as a 3rd parameter
	public static void subscribeToYearlyPlanPL (String baseUrl, WebDriver driver, String plan, String coupon) {
		String xpathToButton = rerutrnXpathToButtonByPlan(baseUrl, driver, plan);
		String country = "PL";
		driver.findElement(By.id("periodicity-yearly")).click();
		fillOutBillingForm(baseUrl, driver, coupon);
		
		
		finishSubscribingByNation(driver, xpathToButton, country);
	}
	
	// Returns the path to the button depending on the plan
	static String rerutrnXpathToButtonByPlan (String baseUrl, WebDriver driver, String plan) {
		
		String xpathToButton = "";
		if (plan != "gold"){
			subscribeToPlanOtherThanGold(baseUrl, driver, plan);
			xpathToButton = ".//*[@id='subscribeForm']/div[3]/div[4]/div[2]/fs-btn-submit/input";
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.get(baseUrl + "/admin/account/billing");
			xpathToButton = ".//*[@id='billingForm']/div[3]/div[4]/div[2]/fs-btn-submit/input";
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return xpathToButton;
	}
	
	
	// finishes subscribing to the plan depending on the nation
	static void finishSubscribingByNation(WebDriver driver, String xpathToButton, String country) {
		
		if(country == "US"){
			
			Select selectCountry = new Select(driver.findElement(By.id("country")));
			selectCountry.selectByVisibleText("United States");
			
			Select selectState = new Select(driver.findElement(By.id("state")));
			selectState.selectByVisibleText("California");
			driver.findElement(By.id("zip")).sendKeys("1234");
			
			driver.findElement(By.xpath(xpathToButton)).click();
			
		} else {
			
			Select selectCountry = new Select(driver.findElement(By.id("country")));
			selectCountry.selectByVisibleText("Poland");
			
			driver.findElement(By.id("stateother")).sendKeys("Pomorskie");
			driver.findElement(By.id("zip")).sendKeys("80-308");
			driver.findElement(By.id("company")).clear();
			driver.findElement(By.id("company")).sendKeys("Company");
			
			driver.findElement(By.xpath(xpathToButton)).click();
			
		}
			
	}
	
	// Reveals diamond and platinum plan since they're not visible straight away
	static void revealHigherPlans (WebDriver driver){
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[2]/a/div[1]/p")).click();
		driver.findElement(By.xpath(".//*[@id='sourceDetails']/div[2]/div[1]/fs-btn[2]/a/div[1]/p")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
