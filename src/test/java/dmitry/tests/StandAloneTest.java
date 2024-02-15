package dmitry.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.findElement(By.id("userEmail")).sendKeys("test@test.org");
		driver.findElement(By.id("userPassword")).sendKeys("2321GbbO");
		driver.findElement(By.id("login")).click();
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.className("mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.tagName("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions actions = new Actions(driver);
		actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "uni").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[6]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			

//		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
//		for (WebElement element : products) {
//			if (element.findElement(By.tagName("h5")).getText().contains(productName)) {
//				
//				element.findElement(By.xpath("button[2]")).click();
//			}
//		}
//		
//		String alertMessage = driver.findElement(By.xpath("//div[@role='alert'][contains(text(),'Product Added To Cart')]")).getText();
//		String itemCountLabel = driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']/label")).getText();
//		
//		assertEquals(alertMessage, "Product Added To Cart");
//		assertEquals(itemCountLabel, "1");
//		
//		driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']/label")).click();
//		
//		WebElement itemNameInTheCart = driver.findElement(By.xpath("//div[@class='cartSection']/h3"));
//		
//		assertEquals(itemNameInTheCart.getText(), productName);
//		
//		driver.findElement(By.cssSelector(".subtotal button")).click();
//		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("uni");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
//		js.executeScript("window.scrollBy(0,2000)");
//		List<WebElement> dropdown = driver.findElements(By.xpath("//section[contains(@class,'ta-results')]//span"));
//		for (WebElement country : dropdown) {
//			if (country.getText().contains("United States")) {
//				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='ta-results list-group ng-star-inserted']//span")));
//				country.click();
//				break;
//			}
//		}
//		
//		driver.findElement(By.cssSelector(".actions a")).click();
//		String confirmationMessage = driver.findElement(By.className("hero-primary")).getText();
//		Assert.assertTrue(confirmationMessage.toLowerCase().contains("Thankyou for the order".toLowerCase()));
//		String orderId = driver.findElement(By.xpath("//tr[@class='ng-star-inserted']")).getText();
//		System.out.println("Order id is " + orderId);
//		Assert.assertTrue(orderId.length()>0);
	
		driver.quit();

		

	}
}
