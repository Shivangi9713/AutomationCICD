package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> productNames;
	
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match =productNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
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

