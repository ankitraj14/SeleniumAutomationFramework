package ankit.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ankit.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// page Factory Method
	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

	@FindBy(css = "li [type*='button']")
	WebElement checkout;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public checkOutPage goToCheckOut() {
		checkout.click();
		return new checkOutPage(driver);
	}
}
