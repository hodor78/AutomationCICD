package dmitry.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class LoginPage extends CommonMethods {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
// PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	@FindBy(css="[class*='flyInOut'")
	WebElement errorMessage;
	
	public ProductPage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitButton.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public String getErrorMessage( ) {
		waitToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void openLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");

	}
	
}
