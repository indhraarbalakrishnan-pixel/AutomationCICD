package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class Orderpage extends Abstractcomponent {
	
	
	WebDriver driver;
	
	public Orderpage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> Orderproducts;
	
	
	
	public Boolean verifyOrdersList(String Productname1) throws InterruptedException
	{
		Thread.sleep(2000);
		Boolean match =  Orderproducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Productname1));
	    return match;   
	    
	}
	
	
	}

