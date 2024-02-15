package dmitry.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dmitry.base.BaseTest;
import dmitry.pages.CartPage;
import dmitry.pages.CheckOutPage;
import dmitry.pages.ConfirmationPage;
import dmitry.pages.OrderPage;
import dmitry.pages.ProductPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductPage productPage = loginPage.login(input.get("email"), input.get("password"));
//		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(input.get("product"));

		CartPage cartPage = productPage.goToCartPage();

		Assert.assertTrue(cartPage.getItemNameInCart(input.get("product")));

		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry("uni");
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// To Verify "ADIDAS ORIGINAL" is displaying in orders page after placing an order
	

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductPage productPage = loginPage.login("test@test.org", "2321GbbO");
		OrderPage orderPage = productPage.goToOrdersPage();
		Assert.assertTrue(orderPage.getDisplayedOrdersName(productName));
	}


	// Extent reports -

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\dmitry\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		  {
//		    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//		    
//		  }

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "test@test.org");
//		map.put("password", "2321GbbO");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "test2@test.org");
//		map2.put("password", "2321GbbO");
//		map2.put("product", "ZARA COAT 3");

	}

}
