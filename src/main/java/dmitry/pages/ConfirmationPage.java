package dmitry.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class ConfirmationPage extends CommonMethods {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "hero-primary")
	WebElement thankyouForOrderMessage;
	
	public String getConfirmationMessage() {
		return thankyouForOrderMessage.getText();
	}
	
}