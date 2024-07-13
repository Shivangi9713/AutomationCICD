package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match =cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage checkout()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].click();", checkoutButton);
		//checkoutButton.click();
		CheckoutPage checkout=new CheckoutPage(driver);
		return checkout;
	}
 
}

