package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	//JavascriptExecutor js = (JavascriptExecutor) driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//this.js=(JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By allCountries= By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement item;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	


	public void selectCountry()
	{
		country.sendKeys("india");
		waitForElementToAppear(allCountries);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", item);
		//item.click();
	}
	
	public MyOrderPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", submit);
		//submit.click();
		MyOrderPage orderPage= new MyOrderPage(driver);
		return orderPage;
	}
}
