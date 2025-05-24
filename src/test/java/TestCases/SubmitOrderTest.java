package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.Retry;


import PageObjectiveModel.CartPage;
import PageObjectiveModel.CheckoutPage;

import PageObjectiveModel.ConfirmationsPage;
import PageObjectiveModel.LoginPage;
import PageObjectiveModel.OrderPage;
import PageObjectiveModel.ProductCatalog;
import TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String country ="India";
	
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
	    try {
	        // Launch the application and log in
	        
	        ProductCatalog productCatalog = loginPage.LoginApplication(input.get("email"), input.get("password"));

	        // Get the list of products and add the specified product to the cart
	        List<WebElement> products = productCatalog.getProdList();
	        productCatalog.addProdToCart(input.get("productName"));

	        // Navigate to the cart page and verify the product is displayed
	        CartPage cartPage = productCatalog.goToCart();
	        boolean isProductInCart = cartPage.varifyProductDisplay(input.get("productName"));
	        Assert.assertTrue(isProductInCart, "Product not found in the cart!");

	        // Proceed to checkout and select the country
	        CheckoutPage checkoutPage = cartPage.goToCheckOut();
	        checkoutPage.SelectCountry(country);

	        // Submit the order and verify the confirmation message
	        ConfirmationsPage confirmationPage = checkoutPage.submitOrder();
	        String confirmationMessage = confirmationPage.getConfirmationMessage();
	        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."),
	                "Order confirmation message mismatch!");
             
	    } catch (Exception e) {
	        // Log the exception for debugging
	        e.printStackTrace();
	        Assert.fail("Test failed due to an exception: " + e.getMessage());
	    }
	}

	@Test(dataProvider="getData")

	public void varifyOrderDisplay(HashMap<String,String> input) throws InterruptedException {

		ProductCatalog p1 = loginPage.LoginApplication(input.get("email"), input.get("password"));
		
		OrderPage or = p1.goToOrdersPage();
		boolean orderMatch = or.VerfiyOrderDisplay(input.get("productName"));
		
		Assert.assertTrue(orderMatch);
		
	}
	
	
    @DataProvider
    public Object[][] getData() throws IOException{
    	       	
    	List<HashMap<String, String>> data= 	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ComponentData\\PurchaseOrder.json");
    	
    	  return new Object[][] {{data.get(0)},{data.get(1)}};
    			  
       }
    
    
}
