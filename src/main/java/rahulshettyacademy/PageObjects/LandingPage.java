package rahulshettyacademy.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class LandingPage extends Abstractcomponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement Email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement Errormessage;

	public void landing(String email, String pass) {
		
		Email.clear();
		Email.sendKeys(email);
		password.clear();
		password.sendKeys(pass);
		elementToBeClickable(submit);
		((JavascriptExecutor) driver)
        .executeScript("arguments[0].click();", submit);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String getErrorMessage()
	{
		elementsToBeAppeared(Errormessage);
		return Errormessage.getText();
		
	}

}
