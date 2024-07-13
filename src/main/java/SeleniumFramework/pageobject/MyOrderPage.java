package SeleniumFramework.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.AbstractComponents.AbstractComponent;

public class MyOrderPage extends AbstractComponent {
	
	WebDriver driver;

	public MyOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String orderMessage()
	{
	  return confirmMessage.getText();
	 
	}
	
	

}
