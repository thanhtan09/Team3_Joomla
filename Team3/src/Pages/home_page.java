package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class home_page extends abstract_page{
	
	public String menu_content = "//ul[@id='menu']/descendant::a[contains(text(),'Content')]";
	public String menu_articlemanager = "//ul[@id='menu']/descendant::a[contains(text(),'Article Manager')]";
		
	public home_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}	
	
	public article_page openArticlepage(){
		hoverElement(By.xpath(menu_content));
		clickElement(By.xpath(menu_articlemanager));
		
		return new article_page(driver);
	}

}
