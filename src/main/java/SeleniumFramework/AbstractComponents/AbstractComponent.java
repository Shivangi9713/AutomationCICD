package SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public CartPage goToCart()
	{
		cart.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrders()
	{
		orderHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
	

	
	public void waitForElementToAppear(By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear( WebElement FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
