package dmitry.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class ProductPage extends CommonMethods {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By products = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By item = By.tagName("b");
	By popupMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitToAppear(products);
		return productList;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream()
				.filter(prod -> prod.findElement(item).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitToAppear(popupMessage);
		waitToDissapear(spinner);

	}

}
