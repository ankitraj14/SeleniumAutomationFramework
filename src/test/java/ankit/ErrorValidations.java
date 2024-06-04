package ankit;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ankit.PageObject.CartPage;
import ankit.PageObject.ProductCatalogue;
import commonTest.BaseTest;
import commonTest.Retry;

public class ErrorValidations extends BaseTest {

	@Test(groups= "ErrorHandling",  retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue pd = landingPage.loginApplication("anshika@gmail.com", "Imking@000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		ProductCatalogue pd = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> products = pd.getProductList();
		pd.addProductToCart(productName);
		Thread.sleep(1000);
		CartPage cartPage = pd.goTocartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		

	}
}
