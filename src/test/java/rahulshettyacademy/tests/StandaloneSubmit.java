package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
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




public class StandaloneSubmit extends Basetest {
	
	String Productname="ZARA COAT 3";
	String Productname1="ADIDAS ORIGINAL";


	
	@Test(dataProvider= "getData")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{
		 
		LandingPage land = new LandingPage(getDriver());
		
		land.landing(input.get("email"), input.get("password"));
		Productcatalogue produ = new Productcatalogue(getDriver());
		List<WebElement> products = produ.getProductsList();
		produ.getProductByList(input.get("Productname1"));
		produ.addToCart(input.get("Productname1"));
		produ.getProductByList1(input.get("Productname"));
		produ.addtoCart1(input.get("Productname"));
		produ.goToCart();
		Cartpage cp = new Cartpage(getDriver());
		cp.verifyProductCatalogue(input.get("Productname1"));
		cp.scroll();
		cp.checkbutton();
		Checkoutpage checkout = new Checkoutpage(getDriver());
		Confirmationpage cmp = checkout.dropdown();
		String cusMsg = cmp.getorderMessage();
		Assert.assertTrue(cusMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
	
	
	
	@DataProvider(parallel=true)
	public Object[][] getData() throws IOException

	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json" );
		
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","indhraabalakrishnan@gmail.com");
		map.put("password", "Hanuman@143");
		map.put("Productname", "ZARA COAT 3");
		map.put("Productname1", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","indhraarbalakrishnan@gmail.com");
		map1.put("password", "Malu@143");
		map1.put("Productname", "ZARA COAT 3");
		map1.put("Productname1", "ADIDAS ORIGINAL");*/
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
}



