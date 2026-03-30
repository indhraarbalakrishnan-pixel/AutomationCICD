package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class Checkoutpage extends Abstractcomponent {

	
	WebDriver driver;
	
	public Checkoutpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	By result = By.cssSelector(".ta-results");
	By invisible = By.cssSelector(".ngx-spinner-overlay");
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement Selectcountry ; 
	
	@FindBy(css=".action__submit")
	WebElement actionbutton;
	
	
	@FindBy(xpath="//tr/td/h1[@class='hero-primary']")
	WebElement orderMessage;

	public Confirmationpage dropdown() throws InterruptedException
	{
		country.sendKeys("India");
		elementsToAppear(result);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		Thread.sleep(1000);
		Selectcountry.click();
		Thread.sleep(1000);
		actionbutton.click();
		elementToBeClickable(orderMessage);
		Thread.sleep(2000);
		Confirmationpage cmp = new Confirmationpage(driver);
		return cmp;
		
	}
	
}
