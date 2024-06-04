package ankit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

//import org.junit.Assert;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ankit.PageObject.CartPage;
import ankit.PageObject.OrderPage;
import ankit.PageObject.ProductCatalogue;
import ankit.PageObject.checkOutPage;
import ankit.PageObject.confirmationPage;
import commonTest.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "purchase")
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		ProductCatalogue pd = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pd.getProductList();
		pd.addProductToCart(input.get("product"));
		Thread.sleep(1000);
		CartPage cartPage = pd.goTocartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.goToConfirmationPage("India");

		Thread.sleep(5000);
		confirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMsg = confirmationPage.verifyConfirmationmsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue pd = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = pd.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product ", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "Sohankumar@gmail.com");
//		map1.put("password", "12345678");
//		map1.put("product ", "ZARA COAT 3");

}
