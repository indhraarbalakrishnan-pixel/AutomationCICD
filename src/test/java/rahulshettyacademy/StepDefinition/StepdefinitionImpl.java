package rahulshettyacademy.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjects.Cartpage;
import rahulshettyacademy.PageObjects.Checkoutpage;
import rahulshettyacademy.PageObjects.Confirmationpage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.Productcatalogue;
import rahulshettyacademy.testcomponents.Basetest;

public class StepdefinitionImpl extends Basetest {
	
	public LandingPage land;
	public Productcatalogue produ ;
	public Cartpage cp;
	public Checkoutpage checkout ;
	public Confirmationpage cmp;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		 launchApplication();
		 land = new LandingPage(getDriver());
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String name, String password)
	{
		land.landing(name,password);
		produ = new Productcatalogue(getDriver());
	}
	
	@When("^I add product (.+) and (.+) from cart$")
	public void i_add_product(String productname, String productname1) throws InterruptedException
	{
		List<WebElement> products = produ.getProductsList();
		produ.getProductByList(productname);
		produ.addToCart(productname);
		produ.getProductByList1(productname1);
		produ.addtoCart1(productname1);
		produ.goToCart();
		cp = new Cartpage(getDriver());
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productname) throws InterruptedException
	{
		
		cp.verifyProductCatalogue(productname);
		cp.scroll();
		cp.checkbutton();
		checkout = new Checkoutpage(getDriver());
		cmp = checkout.dropdown();
	}
	
	@Then("^verify (.+) is displayed on the Confirmationpage$")
	public void verify_message_is_displayed_on_the_Confirmationpage(String message)
	{
		String cusMsg = cmp.getorderMessage();
		Assert.assertTrue(cusMsg.equalsIgnoreCase(message));
		
	}
	
	@Then("^verify (.+) message is displayed$")
	public void verify_message_is_displayed(String message)
	{
		Assert.assertEquals(message, land.getErrorMessage());
		getDriver().close();
	}
}
