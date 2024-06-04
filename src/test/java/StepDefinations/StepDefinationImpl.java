package StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ankit.PageObject.CartPage;
import ankit.PageObject.LandingPage;
import ankit.PageObject.ProductCatalogue;
import ankit.PageObject.checkOutPage;
import ankit.PageObject.confirmationPage;
import commonTest.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue pd;
	public confirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^Logged in  with (.+) and  password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		pd = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to Cart")
	public void i_add_product_to_Cart(String productName) {
		List<WebElement> products = pd.getProductList();
		pd.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		Thread.sleep(1000);
		CartPage cartPage = pd.goTocartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.goToConfirmationPage("India");

		Thread.sleep(5000);
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMsg = confirmationPage.verifyConfirmationmsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string1) {
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
	}
}
