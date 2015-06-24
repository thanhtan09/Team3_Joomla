package Pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;

public abstract class abstract_page {

	protected WebDriver driver;
	protected abstract String getLoadedLocator();
    protected final Log log;
    
    protected abstract_page()
    {
        driver = browser.getBrowser().getDriver();
        log = LogFactory.getLog(getClass());
        log.debug("Created page abstraction for " + getClass().getName());
    }
    
    /*Check control exist
     * Para: control name
     * Return: boolean
     * Creator: Tan Vo
     */
	public boolean isControlEmpty(String controlName) 
	{
		 final boolean isPresent = driver.findElements(By.xpath(controlName)).isEmpty();
		 if(isPresent==false)
			 return true;
		 return false;
	}
	
	/*Wait for control exist
     * Para: xpath, timemout
     * Return: none
     * Creator: Tan Vo
     */
	public void waitForControl(final String controlXPath, long timeout)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);

			Function<WebDriver, WebElement> waitFunction = new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(controlXPath));
				}
			};
			wait.until(waitFunction); 
		} catch (Exception e) {
			log.debug("Element doesn't exist");
		}
	}
	
	/*Wait for page load
     * Para: timemout
     * Return: none
     * Creator: Tan Vo
     */
	public void waitForPageLoaded(long timeout) {
		waitForControl(getLoadedLocator(), timeout);
	}
}
