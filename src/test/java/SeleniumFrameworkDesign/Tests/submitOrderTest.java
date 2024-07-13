package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponents.baseTest;
import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.MyOrderPage;
import SeleniumFramework.pageobject.OrderPage;
import SeleniumFramework.pageobject.ProductCatalog;

public class submitOrderTest extends baseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input)throws IOException {
	//public void submitOrder(String email, String password, String productName) throws IOException {
//now instead of catching values as parameters u can use hashmap
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cart = productCatalog.goToCart();

		Boolean match = cart.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkout = cart.checkout();

		checkout.selectCountry();
		MyOrderPage orderPage = checkout.submitOrder();

		String ConfirmMessage = orderPage.orderMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalog productCatalog = landingPage.loginApplication("shivangisaini@gmail.com", "Hello@123");
		OrderPage orderPage =productCatalog.goToOrders();
		Boolean match= orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*now we are using json so commenting this as we donot need it,json will create automatically
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "shivangisaini@gmail.com");
		map.put("password", "Hello@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "gauravsaini@gmail.com");
		map1.put("password", "Hello@123");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};
		return new Object[][] {{map},{map1}};
		//return new Object[][] {{"shivangisaini@gmail.com","Hello@123", "ZARA COAT 3"},{"gauravsaini@gmail.com","Hello@123","ADIDAS ORIGINAL"}};
	*/
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
