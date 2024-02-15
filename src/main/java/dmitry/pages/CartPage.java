package dmitry.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class CartPage extends CommonMethods {
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public Boolean getItemNameInCart(String productName) {
		return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));		
	}
	

	public CheckOutPage goToCheckOut() {
		checkoutButton.click();
		return new CheckOutPage(driver);
	}
	
	
	
}
