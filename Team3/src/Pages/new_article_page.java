package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class new_article_page extends abstract_page{

	public new_article_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String txt_title = "//input[@id='jform_title']";
	public String drop_category = "//select[@id='jform_catid']";
	public String txt_contain = "";
	public String icon_saveandclose = "//li[@id='toolbar-save']/a";
	
	public void addNew(String _title, String _category){		
		
		enterElement(By.xpath(txt_title), _title);
		selectElement(By.xpath(drop_category), _category);
		clickElement(By.xpath(icon_saveandclose));
	}
}
