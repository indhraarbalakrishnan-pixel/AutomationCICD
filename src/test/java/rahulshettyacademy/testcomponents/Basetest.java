package rahulshettyacademy.testcomponents;

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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest  {
	
	
	
	
	public static ThreadLocal<WebDriver> threadlocaldriver = new ThreadLocal<>();

	  
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties pts = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//rahulshettyacademy//resources//Globaldata.properties");
		pts.load(fis);
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : pts.getProperty("browser");
				//pts.getProperty("browser");
		
		if(browsername.contains("chrome"))
		{
			
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browsername.contains("headless")) {
			options.addArguments("headless");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
		}
		
		threadlocaldriver.set(new ChromeDriver(options));
		
		
		}
		
		else if(browsername.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			threadlocaldriver.set(new EdgeDriver());
		}
		
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			threadlocaldriver.set(new FirefoxDriver());
			
		}
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
		
		}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	String jsoncontent = 	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
	//convert string to hashMap - using jackson databind
	
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testcaseName) throws IOException

	{
	TakesScreenshot ts = (TakesScreenshot)getDriver();
	File src = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"\\reports\\" + testcaseName + ".png");
	FileUtils.copyFile(src, file);
	return System.getProperty("user.dir")+"\\reports\\" + testcaseName + ".png";
	
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException
	
	{
		initializeDriver();
		getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
	   
		
		
	}
	
	  public static WebDriver getDriver() {
	        return threadlocaldriver.get();
	    }

	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		  if (getDriver() != null) {
		        getDriver().quit();
		        threadlocaldriver.remove();
		    }
	}
	}
