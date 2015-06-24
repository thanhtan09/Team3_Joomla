package Functions;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class common {

	static WebDriver driver;
	public static StringBuffer verificationErrors = new StringBuffer();
	
	public static WebDriver openJoomla(String _browser) {
		if (_browser.equals("Firefox")) {
			driver = new FirefoxDriver();
			driver.get("http://localhost:8080/Joomla/administrator/index.php");
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	public static void Teadown(WebDriver driver) {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
