package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjectiveModel.CartPage;
import PageObjectiveModel.CheckoutPage;
import PageObjectiveModel.ConfirmationsPage;
import PageObjectiveModel.LoginPage;
import PageObjectiveModel.ProductCatalog;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepFDefinitionImp extends BaseTest {

	public  LoginPage loginPage;
	public ProductCatalog productCatalog;
	public String country = "India";
	public ConfirmationsPage confirmationPage;
	
	
	@Given("I landed on Ecommerce on page")
	public void i_landed_on_Ecommerce_on_page() throws IOException {
		
	loginPage=	LaunchApplication();
			
	}
	
	@Given("^login with username(.+) ans password(.+)$")
	public void login_with_username_and_password(String username, String password) throws IOException {
	 productCatalog= loginPage.LoginApplication(username, password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProdList();
        productCatalog.addProdToCart(productName);
		
}
	
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalog.goToCart();
        boolean isProductInCart = cartPage.varifyProductDisplay(productName);
        Assert.assertTrue(isProductInCart, "Product not found in the cart!");

        // Proceed to checkout and select the country
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.SelectCountry(country);

        // Submit the order and verify the confirmation message
        confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String message) {
	    // Validate the error message on the login page
	    String actualMessage = loginPage.getErrMsg();
	    Assert.assertEquals(actualMessage, message, "Error message does not match!");
	    driver.close();
	}

	@Then("{string} message is displayed on confrmPage")
	public void message_is_displayed_on_confrmPage(String message) {
	    // Validate the confirmation message on the confirmation page
	    String actualMessage = confirmationPage.getConfirmationMessage();
	    Assert.assertEquals(actualMessage, message, "Confirmation message does not match!");
	    driver.close();
	}


}
