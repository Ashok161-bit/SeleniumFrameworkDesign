package TestCases;

import java.io.IOException;
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
import org.testng.annotations.Test;

import TestComponents.Retry;
import PageObjectiveModel.CartPage;
import PageObjectiveModel.CheckoutPage;
import PageObjectiveModel.ConfirmationsPage;
import PageObjectiveModel.LoginPage;
import PageObjectiveModel.ProductCatalog;
import TestComponents.BaseTest;


	
public class ErrorValidation extends BaseTest {

	@Test
    public void loginErrorValidation() throws IOException {

        
        loginPage.LoginApplication("ashokpinninti77@gmail.com","Rahul@#1233");
        Assert.assertEquals("Incorrect email or password.", loginPage.getErrMsg());        
        
    }
       
     @Test 
    public void productErrorValidation() throws IOException, InterruptedException {

        String productname = "ZARA COAT 3";
        String country ="India";
        
        
        LoginPage loginpage = LaunchApplication();
        ProductCatalog productcatlog = loginpage.LoginApplication("ashokpinninti77@gmail.com", "Rahul@#123");
      
        List<WebElement> products = productcatlog.getProdList();
        productcatlog.addProdToCart(productname);
        
        CartPage cartpage = productcatlog.goToCart();
        
        Boolean match= cartpage.varifyProductDisplay(productname);
        Assert.assertFalse(match); 
              
    } 
}



