package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.Cartpage;
import rahulshettyacademy.PageObjects.Checkoutpage;
import rahulshettyacademy.PageObjects.Confirmationpage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.Productcatalogue;
import rahulshettyacademy.testcomponents.Basetest;



@SuppressWarnings("restriction")
public class Parameterization extends Basetest {
	
	String Productname="ZARA COAT 3";
	String Productname1="ADIDAS ORIGINAL";


	
	@Test(dataProvider= "getData",groups= {"Purchase"})
	public void submitOrder(String email, String password, String Productname,String Productname1) throws InterruptedException, IOException
	{
		 

		LandingPage land = new LandingPage(getDriver());
		land.goTo();
		land.landing(email, password);
		Productcatalogue produ = new Productcatalogue(getDriver());
		List<WebElement> products = produ.getProductsList();
		produ.getProductByList(Productname1);
		produ.addToCart(Productname1);
		produ.getProductByList1(Productname);
		produ.addtoCart1(Productname);
		produ.goToCart();
		Cartpage cp = new Cartpage(getDriver());
		cp.verifyProductCatalogue(Productname1);
		cp.scroll();
		cp.checkbutton();
		Checkoutpage checkout = new Checkoutpage(getDriver());
		Confirmationpage cmp = checkout.dropdown();
		String cusMsg = cmp.getorderMessage();
		Assert.assertTrue(cusMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
	 
	
	
	@DataProvider(parallel=true)
	public Object[][] getData()
	{
		return new Object[][] {{"indhraabalakrishnan@gmail.com", "Hanuman@143", Productname,Productname1},{"indhraarbalakrishnan@gmail.com", "Malu@143", Productname ,Productname1}};
	}
}



