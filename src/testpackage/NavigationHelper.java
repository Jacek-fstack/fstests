package testpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/* Class handles navigating to specific URL or app sections*/

public class NavigationHelper {
	
	
	public static void navigateToPlanList(String baseUrl, WebDriver driver){
		
		// Navigate only if there is no Edit Billing Info button visible.
		if (driver.findElements(By.xpath(".//*[@id='sourceDetails']/div[1]/div[2]/table/tbody/tr/td[2]/fs-btn/a/div[1]/p")).size() == 0){
			
			// Sleep 3 seconds, so the app does not go bananas from clicking too quickly
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.get(baseUrl + "/admin/account/billing");
			driver.findElement(By.linkText("Form Plans")).click();
		}
		
	}
	
	// Navigates to the users page (adding and removing users)
	public static void navigateToUsersList(String baseUrl, WebDriver driver){

		checkUrlAndSwitchIfNeeded(baseUrl, driver, "/admin/account/users");
	}
	
	// Navigates to the form list tab
	public static void navigateToFormsTab(String baseUrl, WebDriver driver){
		
		checkUrlAndSwitchIfNeeded(baseUrl, driver, "/admin/form/dashboard/folder/uncategorized");
	}

	
	// General method for checking if the user is on a proper page, if he is not - redirects the browser
	static void checkUrlAndSwitchIfNeeded(String baseUrl, WebDriver driver, String address){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String url = driver.getCurrentUrl();
		if(url != baseUrl + address ){
			driver.get(baseUrl + address);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

	
}
