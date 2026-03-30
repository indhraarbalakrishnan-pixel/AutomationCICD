package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rahulshettyacademy.testcomponents.Retry;

import rahulshettyacademy.PageObjects.Cartpage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.Productcatalogue;
import rahulshettyacademy.testcomponents.Basetest;



@SuppressWarnings("restriction")
public class Errorvalidations extends Basetest {
	
	String Productname="ZARA COAT 3";
	String Productname1="ADIDAS ORIGINAL";
	
	 public LandingPage land;
	 
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException
	{
		

		LandingPage land = new LandingPage(getDriver());
		land.landing("indhraabalakrishnan@gmail.com", "Hanan@143");
		Assert.assertEquals("Incorrect email or password.", land.getErrorMessage());
		//.ng-tns-c4-7.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		
	
	}
	
	 
	@Test
	public void ProductErrorValidation() throws InterruptedException
	{
		
		LandingPage land = new LandingPage(getDriver());
		
		land.landing("backupindhraa28@gmail.com", "Saibaba@2810");
		Productcatalogue produ = new Productcatalogue(getDriver());
		List<WebElement> products = produ.getProductsList();
		produ.getProductByList(Productname1);
		produ.addToCart(Productname1);
		produ.getProductByList1(Productname);
		produ.addtoCart1(Productname);
		produ.goToCart();
		Cartpage cp = new Cartpage(getDriver());
		Boolean match = cp.verifyProductCatalogue(Productname1);
		Assert.assertTrue(match);
	}
}



