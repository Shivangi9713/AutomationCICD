package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import SeleniumFramework.TestComponents.Retry;

import SeleniumFramework.TestComponents.baseTest;
import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.ProductCatalog;

public class ErrorValidationsTest extends baseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		 landingPage.loginApplication("shivangisaini@gmail.com", "hshsho@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation()
	{
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("gauravsaini@gmail.com", "Hello@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cart = productCatalog.goToCart();
		Boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
