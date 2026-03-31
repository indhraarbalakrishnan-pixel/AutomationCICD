package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;


@SuppressWarnings("restriction")
public class Standalonetest {
	
	public static void main(String[]args) throws InterruptedException 
	{
		String Productname="ZARA COAT 3";
		String Productname1="ADIDAS ORIGINAL";
		//System.setProperty("Webdriver.chrome.driver", "C:\\Users\\Gausalya\\Documents\\chromedriver-win64");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("indhraabalakrishnan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hanuman@143");
		driver.findElement(By.id("login")).click();
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equals(Productname1)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebElement prod1 = products.stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
		WebElement cartButton = prod1.findElement(By.cssSelector(".card-body button:last-of-type"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		    List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		    Thread.sleep(3000);
		    
		    cartProducts.forEach(cartProduct -> 
		    System.out.println("Cart item text: [" + cartProduct.getText() + "]"));

		
		    Boolean match =   cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Productname1));
		 
		    System.out.println(match);
		    Thread.sleep(1000);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1050)");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

			WebElement checkoutBtn = driver.findElement(By.xpath("//button[text()='Checkout']"));

			wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

			driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
			driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
			driver.findElement(By.cssSelector(".action__submit")).click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tr/td/h1[@class='hero-primary']"))));
		    String cusMsg = driver.findElement(By.xpath("//tr/td/h1[@class='hero-primary']")).getText();
		    Assert.assertTrue(cusMsg.equalsIgnoreCase("Thankyou for the order."));
	}
}
