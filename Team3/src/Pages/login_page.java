package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class login_page extends abstract_page {
	public login_page(WebDriver _driver) {
		super(_driver);
	}

	protected abstract_page abstractpage;
	
	/*Element of Login Page
	 * Creator: Tan Vo
	 */
	private String txt_username = "//input[@name='username']";
	private String txt_password = "//input[@name='passwd']";
	private String drop_language = "//select[@id='lang']";
	private String btn_login = "//div[@class='next']/descendant::a";
	
	
	/*login
	 * para: username, password
	 * return: none
	 * creator: Tan Vo
	 */
	public void login(String _username, String _pass){		
		enterElement(By.xpath(txt_username),_username);
		enterElement(By.xpath(txt_password),_pass);
		clickElement(By.xpath(btn_login));
	}

	
}
