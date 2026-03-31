package rahulshettyacademy.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.Abstractcomponent;

public class Productcatalogue extends Abstractcomponent{

	WebDriver driver;

	public Productcatalogue (WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement waitelem;

	
	By productsBy = By.cssSelector(".mb-3");
	By addcart = By.cssSelector(".card-body button:last-of-type");
	By invisible = By.cssSelector(".ngx-spinner-overlay");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList()
	{
		elementsToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByList(String Productname1)
	
	{
		WebElement prod = getProductsList().stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equals(Productname1)).findFirst().orElse(null);
		return prod;
		
	}
	
	
	public void addToCart(String Productname1)
	{

    WebElement prod = getProductByList(productName);
	WebElement addToCartBtn = prod.findElement(addcart);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfAllElements(products));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(invisible));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
    addToCartBtn.click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(toastMessage));
}
	
	
public WebElement getProductByList1(String Productname)
	
	{
		 WebElement prod1 = products.stream().filter(produc ->produc.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
		 return prod1;
		
		
	}
	
   public void addtoCart1(String Productname) throws InterruptedException {
	   
	/*Thread.sleep(1000);
	elementsToDisappear(invisible);
	elementClickable(addcart);
	getProductByList1(Productname).findElement(addcart).click();
	elementsToAppear(toastMessage);
	waitToDisappear(waitelem);*/
	   List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	    WebElement prod = products.stream()
	            .filter(product -> product.findElement(By.cssSelector("b"))
	            .getText().equals(Productname))
	            .findFirst().orElse(null);

	    WebElement addToCart = prod.findElement(By.cssSelector(".card-body button:last-of-type"));

	    // Scroll to element
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);

	    // Wait until clickable
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(addToCart));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
	    
   }
	
}
	
	
	

