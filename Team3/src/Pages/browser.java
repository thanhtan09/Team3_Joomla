package Pages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class browser {

	public static synchronized browser getBrowser()
	{
		if ( instance == null ) {
			instance = new browser();
		}
		return instance;
	}

	
	public WebDriver getDriver()
	{
		return driver;
	}

	public void launch(final String driverClass, final String homeUrl, final String grid, String ipClient, String port) throws Exception
	{
	   
		 if(grid.toLowerCase().equals("no"))
         {
			 if (driverClass.contains("firefox")) {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setAssumeUntrustedCertificateIssuer(false);
					driver = new FirefoxDriver(profile);
				} else if (driverClass.contains("ie"))
				{
					// Method and Description - static DesiredCapabilities internetExplorer()
					 DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					 
					 //Method and Description - void setCapability(java.lang.String capabilityName, boolean value)
					 capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					 
					 //Among the facilities provided by the System class are standard input, standard output, and error output streams; access to externally defined "properties"; a means of loading files and libraries; and a utility method for quickly copying a portion of an array.
					 System.setProperty("webdriver.ie.driver", "..\\TestNGandSelenium\\driver\\IEDriverServer.exe");
					 
					 //InternetExplorerDriver(Capabilities capabilities)
					 driver = new InternetExplorerDriver(capabilities); 
					 
				} else if(driverClass.contains("chrome"))
				{
					// Method and Description - static DesiredCapabilities chrome()
					 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					 
					 //Among the facilities provided by the System class are standard input, standard output, and error output streams; access to externally defined "properties"; a means of loading files and libraries; and a utility method for quickly copying a portion of an array.
					 System.setProperty("webdriver.chrome.driver", "..\\TestNGandSelenium\\driver\\chromedriver.exe");
					 
					 //ChromeDriver(Capabilities capabilities)
					 driver = new ChromeDriver(capabilities); 
				}
					driver.manage().window().maximize();
					driver.get(homeUrl);
					this.homeUrl = homeUrl; 
         }else
         {
        	 DesiredCapabilities capability = null;
        	 if (driverClass.contains("firefox"))
        	 {
        		 	capability = DesiredCapabilities.firefox();
					capability.setBrowserName("firefox");
					capability.setPlatform(org.openqa.selenium.Platform.ANY);
					capability.setJavascriptEnabled(true);
					
        	 }else if (driverClass.contains("ie"))
        	 {
        		 	System.setProperty("webdriver.ie.driver", "..\\TestNGandSelenium\\driver\\IEDriverServer.exe");
        		 	capability = DesiredCapabilities.internetExplorer();
					capability.setBrowserName("internetexplorer");
					capability.setPlatform(org.openqa.selenium.Platform.ANY);
					capability.setJavascriptEnabled(true);
					
        	 }else if(driverClass.contains("chrome"))
        	 {
        		 	System.setProperty("webdriver.chrome.driver", "..\\TestNGandSelenium\\driver\\chromedriver.exe");
        		 	capability = DesiredCapabilities.chrome();
					capability.setBrowserName("chrome");
					capability.setPlatform(org.openqa.selenium.Platform.ANY);
					capability.setJavascriptEnabled(true);
        	 }
        	driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub",ipClient,port)), capability);
        	driver.manage().window().maximize();
			driver.get(homeUrl);
			this.homeUrl = homeUrl; 
         }
		
	}

	public void takeScreenshot(String name)
	{
		TakesScreenshot view = TakesScreenshot.class.cast(driver);
		File screenshot = view.getScreenshotAs(OutputType.FILE);
		File destination = new File(name + ".png");
		try {
			FileUtils.copyFile(screenshot, destination);
			log.info("Screenshot saved to " + destination.getAbsolutePath());
		} catch (IOException e) {
			log.error("Failed to write screenshot to " + 
					destination.getAbsolutePath(), e);
		}
	}

	public void goHome()
	{
		open(homeUrl);
		
	}

	public void open(String url)
	{
		driver.get(url);
	}

	public void rememberLocation()
	{
		rememberedUrl = driver.getCurrentUrl();
	}

	public void recallLocation()
	{
		if ( rememberedUrl != null ) {
			driver.get(rememberedUrl);
		}
	}

	public void shutdown()
	{
		driver.quit();
		instance=null;
	}

	

	/**
	 * Some browser actions may open a new window. This method will push the current handle onto a stack,
	 * discover the new handle, then switch to the new window. This will only work if we open one window
	 * at a time, operate on the new window, then close it.
	 */
	public void registerNewWindow()
	{
        openWindowHandles.push(driver.getWindowHandle());
        
        // Find the new handle by the intersection of the open window handles and the entire set.
	    Set<String> newHandles = driver.getWindowHandles();
	    newHandles.removeAll(openWindowHandles);
	    if ( newHandles.size() != 1 ) {
	        log.error("The register new window stack is out of sync.");
	    }
	    String handle = newHandles.iterator().next();
	    
	    driver.switchTo().window(handle);
	}
	
	public void closeNewWindow()
	{
	    if ( openWindowHandles.size() < 1 ) {
	        log.error("Called close new window when only the main browser was open.");
	    } else {
	        driver.close();
	        driver.switchTo().window(openWindowHandles.pop());
	    }
	}
	
	private browser() {}

	private static browser instance = null;
	private WebDriver driver = null;
	private String homeUrl = null;
	private String rememberedUrl = null;
	
	private final Stack<String> openWindowHandles = new Stack<String>();
	
	private static final Log log = LogFactory.getLog(browser.class);
}
