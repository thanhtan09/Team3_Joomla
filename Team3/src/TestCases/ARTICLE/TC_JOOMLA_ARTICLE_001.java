package TestCases.ARTICLE;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Functions.common;
import Pages.article_page;
import Pages.home_page;
import Pages.login_page;
import Pages.new_article_page;

public class TC_JOOMLA_ARTICLE_001 {
	
	WebDriver driver;
	login_page loginpage;
	home_page homepage;
	article_page articlepage;
	new_article_page newarticlepage;
	
	@BeforeMethod
	public void setUp() {
		driver = common.openJoomla("Firefox");
		loginpage = new login_page(driver);
		homepage = new home_page(driver);
		articlepage = new article_page(driver);
		newarticlepage = new new_article_page(driver);
	}
	
	@Test
	public void Verify_user_can_create_new_article_with_valid_information(){		
		loginpage.login("admin","12345");
		homepage.openArticlepage();
		articlepage.openNewarticlepage();
		newarticlepage.addNew("A", "Sample Data-Articles");
	}
	
	@AfterMethod
	public void tearDown(){
		common.Teadown(driver);
	}
}
