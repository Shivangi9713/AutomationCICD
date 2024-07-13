package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initialiseDriver() throws IOException
	{
		//properties class
		
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//SeleniumFramework//Resources//GlobalData.properties");
		prop.load(fis);
		//below step will check if browser is mentioned in terminal if yes then preference is given to terminal
		//below is if cond is true then 1st argumnt execute else 2nd
		// syntax con? 1st arg : 2nd arg
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); 
		//String browserName= prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options =new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("Webdriver.gecko.driver", "C:\\Users\\saini\\OneDrive\\Documents\\geckodriver.exe");
			 driver = new FirefoxDriver();
			//firefox
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initialiseDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//1.read json to string
	 String jsonContent= FileUtils.readFileToString(new File(filePath),
			 StandardCharsets.UTF_8);
	 
	 // 2. convert string to Hashmap->import jacson databind dependency
	 ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>
	(){});
	return data;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;		
		File source = ts.getScreenshotAs(OutputType.FILE);		
		File file = new File(System.getProperty("user.dir")+"/reports/" + testCaseName + ".png");				
		FileUtils.copyFile(source, file);		
		return System.getProperty("user.dir")+ "/reports/" + testCaseName + ".png";
	}

}
