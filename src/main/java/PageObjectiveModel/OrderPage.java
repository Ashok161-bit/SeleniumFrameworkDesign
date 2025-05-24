package PageObjectiveModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponent.BaseClass;

public class OrderPage extends BaseClass {
	
	WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements
    @FindBy(xpath="//tr[@class='ng-star-inserted']//td[2]")
	List<WebElement> cartproduct;
    
   
    
    //driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

    
    public Boolean VerfiyOrderDisplay(String productname) {
    	
        Boolean match=  cartproduct.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
        return match;
       
    }
   
}
	

