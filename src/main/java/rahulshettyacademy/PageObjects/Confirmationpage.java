package rahulshettyacademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class Confirmationpage extends Abstractcomponent {
	
WebDriver driver;
	
	public Confirmationpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tr/td/h1[@class='hero-primary']")
	WebElement confirmMsg;
	
	public String getorderMessage()
	{
		String cusMsg = confirmMsg .getText();
		return cusMsg;
	}

}
