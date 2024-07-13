package SeleniumFramework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.TestComponents.baseTest;
import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.MyOrderPage;
import SeleniumFramework.pageobject.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends baseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public MyOrderPage orderPage;
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage =launchApplication();
	}
	
	  @Given ("^Logged in with username (.+) and password (.+)$")
	  public void logged_in_with_username_and_password(String username, String password)
	  {
		   productCatalog = landingPage.loginApplication(username,password);
	  }
	  
	  @When ("^I add product (.+) to cart$")
	  public void i_add_product_to_cart(String productName)
	  {
		  List<WebElement> products = productCatalog.getProductList();
			productCatalog.addProductToCart(productName);
	  }
	  
	  @When ("^checkout (.+) and submit order$")
	  public void checkout_and_submit_order(String productName)
	  {
		  CartPage cart = productCatalog.goToCart();

			Boolean match = cart.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkout = cart.checkout();
			checkout.selectCountry();
			orderPage = checkout.submitOrder();
	  }
	  
	  @Then ("{string} message is displayed on confirmationPage")
	  public void message_is_displayed_on_confirmationPage(String string)
	  {
		  String ConfirmMessage = orderPage.orderMessage();
			Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
			driver.close();
	  }
	  
	  @Then ("{string} error message is displayed")
	  public void error_message_is_displayed(String string)
	  {
		  Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		  driver.close();
	  }
	  
	  

}
