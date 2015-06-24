package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class abstract_page {

	protected WebDriver driver;
	
	public abstract_page(WebDriver driver){
		this.driver = driver;
	}
	
	public login_page navigatetoLoginpage(){
		driver.navigate().to("http://localhost:8080/Joomla/administrator/index.php");
		return new login_page(driver);
	}
	
	/*Enter value to element
	 * para: by, value
	 * creator: Tan Vo
	 */
	public void enterElement(By by, String _value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(_value);
	}
	
	/*Click on element
	 * Para: by
	 * Creator: Tan Vo
	 */
	public void clickElement(By by){
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	/*Hover on element
	 * Para: by
	 * Creator: Tan Vo
	 */
	public void hoverElement(By by){
		WebElement element = driver.findElement(by);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();
	}
	
	/*Select element
	 * Para: by
	 * Creator: Tan Vo
	 */
	public void selectElement(By by, String _value){
		WebElement element = driver.findElement(by);
		element.sendKeys(_value);
	}
}
