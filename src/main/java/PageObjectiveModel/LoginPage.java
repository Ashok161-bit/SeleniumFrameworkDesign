package PageObjectiveModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.BaseClass;

public class LoginPage extends BaseClass {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userPassword")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(css = ".ng-trigger-flyInOut")
    private WebElement errMsg;

    public ProductCatalog LoginApplication(String email, String pass) {
        try {
            waitForEleToAppear(emailField);
            emailField.sendKeys(email);

            waitForEleToAppear(passwordField);
            passwordField.sendKeys(pass);

            //waitForEleToClickAble(loginButton);
            loginButton.click();

            return new ProductCatalog(driver);
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            throw e;
        }
    }

    public String getErrMsg() {
        waitForEleToAppear(errMsg);
        return errMsg.getText();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
