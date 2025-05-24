package PageObjectiveModel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponent.BaseClass;

public class CheckoutPage extends BaseClass {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
        PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
        WebElement country;
        
        @FindBy(xpath="(//button[@type='button'])[2]")
        WebElement SelectCountry;
        
        @FindBy(xpath="//a[contains(text(),'Place Order ')]")
        WebElement submit;
        
       By result=By.cssSelector(".ta-results");
                   
      public void SelectCountry(String countryName) {
    	  
    	  Actions a= new Actions(driver);
    	  
          a.sendKeys(country,countryName ).build().perform();
          waitForEleToAppear(result);
          
          SelectCountry.click();
      }
      
      public ConfirmationsPage submitOrder() {
    	  
    	  submit.click();
    	  
    	 return new ConfirmationsPage(driver);
      }
}
