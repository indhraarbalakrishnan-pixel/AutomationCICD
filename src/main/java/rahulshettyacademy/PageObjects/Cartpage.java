package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class Cartpage extends Abstractcomponent {
	
	
	WebDriver driver;
	
	public Cartpage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutBtn ;
	
	
	
	By spinner = By.cssSelector(".ngx-spinner-overlay");
	By toast = By.cssSelector("#toast-container");
	
	public Boolean verifyProductCatalogue(String Productname1) throws InterruptedException
	{
		Thread.sleep(3000);
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Productname1));
	    return match;
	    
	    
	}

	public void scroll() throws InterruptedException
	{
		Thread.sleep(1000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1050)");
	}
	
	public void checkbutton() throws InterruptedException
	{
		Thread.sleep(1000);
		elementsToDisappear(spinner);
		elementsToDisappear(toast);
		elementToBeClickable(checkoutBtn);
		checkoutBtn.click();
	}
}
