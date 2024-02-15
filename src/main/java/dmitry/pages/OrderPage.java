package dmitry.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class OrderPage extends CommonMethods {
	
	WebDriver driver;
	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> ordersList;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	public Boolean getDisplayedOrdersName(String productName) {
		return ordersList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));		
	}

}
