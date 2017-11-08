package testpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestScenario {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "/Users/jacekadamczyk/Documents/tests/Drivers/geckodriver");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "/Users/jacekadamczyk/Documents/tests/Drivers/chromedriver");
		//driver = new ChromeDriver();
		String baseUrl = "http://www.dev-formstack.com";
		
		
		//create user
		 AccountManager.createAccount(baseUrl, driver);
		// AccountManager.logInWithExistingUser(baseUrl, driver, "jacektestingacc+fpkwu2e8@gmail.com", "123456");
		 AccountManager.addUser(baseUrl, driver);
		// AccountManager.addUser(baseUrl, driver);
		
		// FormManager.createTemplateForm(baseUrl, driver, "Wedding RSVP");
		
		 SubscribeToPlan.subscribeToPlanUS(baseUrl, driver, "gold");
		// SubscribeToPlan.subscribeToPlanPL(baseUrl, driver, "gold", "TEST123");
	    // SubscribeToPlan.subscribeToYearlyPlanUS(baseUrl, driver, "platinum");
		// SubscribeToPlan.subscribeToYearlyPlanPL(baseUrl, driver, "gold");
				
		// FormManager.createTemplateForm(baseUrl, driver, "Wedding RSVP");
		
		//Migrate to plan:
		// MigratePlan.switchToYearlyBilling(baseUrl, driver);
		 MigratePlan.migrateToPlan(baseUrl, driver, "enterprise");
		 
		// FormManager.createBlankForm(baseUrl, driver);
		// FormManager.createBlankForm(baseUrl, driver);
	
			
		 //FormManager.createBlankWorkflow(baseUrl, driver);
		// FormManager.createTemplateWorkflow(baseUrl, driver, "Wedding RSVP");
		 FormManager.createTemplateForm(baseUrl, driver, "Wedding RSVP");
		 FormManager.createBlankForm(baseUrl, driver);

		// DeactivateOrDeleteAccount.deactivateAccount(baseUrl, driver);
		// DeactivateOrDeleteAccount.deleteAccount(baseUrl, driver);
	}

}
