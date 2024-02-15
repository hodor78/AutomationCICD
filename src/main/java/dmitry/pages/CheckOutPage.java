package dmitry.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dmitry.support.CommonMethods;

public class CheckOutPage extends CommonMethods {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountryField;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[6]")
	WebElement selectUSA;
	
	By countiesList = By.xpath("//section[contains(@class,'ta-results')]");

	public void selectCountry(String counntry) {
		Actions actions = new Actions(driver);
		actions.sendKeys(selectCountryField, counntry).build().perform();
		waitToAppear(countiesList);
		selectUSA.click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}