package ankit.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ankit.AbstractComponents.AbstractComponent;

public class checkOutPage extends AbstractComponent {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(xpath = "(//button[@type='button'])")
	WebElement visibleButton;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectIndia;

	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement submit;

	By visiBli = By.xpath("(//button[@type='button'])");

	public void goToConfirmationPage(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, country).build().perform();
		waitForElementToAppear(visiBli);
		selectIndia.click();
	}

	public confirmationPage submitOrder() throws InterruptedException {
		Thread.sleep(5000);
		submit.click();
		return new confirmationPage(driver);
	}

}
