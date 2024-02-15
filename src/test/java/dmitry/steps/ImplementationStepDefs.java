package dmitry.steps;

import java.io.IOException;
import org.testng.Assert;

import dmitry.base.BaseTest;
import dmitry.pages.CartPage;
import dmitry.pages.CheckOutPage;
import dmitry.pages.ConfirmationPage;
import dmitry.pages.LoginPage;
import dmitry.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ImplementationStepDefs extends BaseTest {

	public LoginPage loginPage;
	public ProductPage productPage;
	public ConfirmationPage confirmationPage;
	@Given("I open Login Page")
	public void i_open_login_page() throws IOException {
		loginPage = launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$") // regex works only if data provided from Examples in feature file
	public void iLoggedInWithUsernameAndPassword(String username, String password) {
		productPage = loginPage.login(username, password);

	}
	
	@When("^I add product (.+) to cart$")
	public void iAddProductToCart(String product) throws InterruptedException {
//		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(product);
	}
	
	
	@And("^I checkout product (.+) and submit the order$")
	public void iCheckoutAndSubmitProduct(String product)  {
		CartPage cartPage = productPage.goToCartPage();

		Assert.assertTrue(cartPage.getItemNameInCart(product));

		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry("uni");
		confirmationPage = checkOutPage.placeOrder();
	}
	
	@Then("I verify {string} message is displayed on Confirmation Page")
	public void iVerifyMessageIsDisplayedOnConfirmationPage(String message) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();;
	}
	
	@Then("I verify {string} message is displayed")
    public void iVerifyErrorMessageIsDisplayed(String message) {
		
		Assert.assertEquals(message, loginPage.getErrorMessage());
		driver.close();
	}
}
