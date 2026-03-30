package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.Orderpage;
import rahulshettyacademy.testcomponents.Basetest;



public class Orderverification extends Basetest {
	
	String Productname="ZARA COAT 3";
	String Productname1="ADIDAS ORIGINAL";

	
	
	 
	@Test(dataProvider="getData")
	public void orderHistoryTest(String email, String password, String Productname1) throws InterruptedException
	{
		
		LandingPage land = new LandingPage(getDriver());
		land.goTo();
		
		land.landing(email, password);
		Orderpage order = new Orderpage(getDriver());
		order.goToOrders();
		Assert.assertTrue(order.verifyOrdersList(Productname1));
		}
	
	@DataProvider(parallel=true)
	public Object[][] getData()
	{
		return new Object[][] {{"indhraabalakrishnan@gmail.com", "Hanuman@143", Productname1},{"indhraarbalakrishnan@gmail.com", "Malu@143",Productname1}};
	}
}



