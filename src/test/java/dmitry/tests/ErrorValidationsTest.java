package dmitry.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import dmitry.base.BaseTest;
import dmitry.base.Retry;
import dmitry.pages.CartPage;
import dmitry.pages.ProductPage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorMessageValidation() {
		
		loginPage.login("test@test.org", "2321Gbb");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
	}

	@Test
	public void noOtherItemsinCartValidation() throws InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		ProductPage productPage = loginPage.login("test@test.org","2321GbbO");
//		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);
		
		
		CartPage cartPage = productPage.goToCartPage();
		
		Assert.assertFalse(cartPage.getItemNameInCart("IPHONE"));
					
	}
}
