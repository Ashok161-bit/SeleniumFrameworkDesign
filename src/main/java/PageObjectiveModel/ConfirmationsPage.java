package PageObjectiveModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.BaseClass;

public class ConfirmationsPage extends BaseClass {

	WebDriver driver;
	public ConfirmationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
        PageFactory.initElements(driver, this);

		// TODO Auto-generated constructor stub
	}
	@FindBy(className="hero-primary")
   WebElement confirmationpage;
	
   public String getConfirmationMessage() {
	  
			 String confmMsg= confirmationpage.getText();
			 return confmMsg;
   }
   
}
