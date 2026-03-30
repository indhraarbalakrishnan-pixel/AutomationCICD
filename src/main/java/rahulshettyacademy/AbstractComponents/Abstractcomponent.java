package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObjects.Cartpage;
import rahulshettyacademy.PageObjects.Orderpage;

public class Abstractcomponent {
	
	WebDriver driver;
	
	public Abstractcomponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement Orders;
	
	public Cartpage goToCart() throws InterruptedException
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		    wait.until(ExpectedConditions.elementToBeClickable(cart));

		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cart);

		    try {
		    	cart.click();
		    } catch (Exception e) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
		    }

		    return new Cartpage(driver);
	}
	
	public Orderpage goToOrders() throws InterruptedException
	{
		Thread.sleep(1000);
		Orders.click();
		Orderpage order = new Orderpage(driver);
		return order;
	}
	
	public void elementsToAppear(By FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void elementsToBeAppeared(WebElement FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	public void elementsToDisappear(By FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
	}
	
	public void elementClickable(By FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	public void waitToDisappear(WebElement FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}
	
	public void elementToBeClickable(WebElement FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}

}
