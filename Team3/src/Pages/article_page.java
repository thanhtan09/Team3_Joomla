package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class article_page extends abstract_page{

	public article_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String icon_new = "//span[@class='icon-32-new']";
	
	public new_article_page openNewarticlepage(){
		clickElement(By.xpath(icon_new));		
		return new new_article_page(driver);
	}
	
}
