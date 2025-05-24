package TestCases;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandloneTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String item = "ZARA COAT 3";
		String country ="India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("ashokpinninti77@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@#123");
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		
		List<WebElement> element = driver.findElements(By.cssSelector(".mb-3"));

		
		
		WebElement product = element.stream().filter(s->s.findElement(By.cssSelector("b")).getText().contains(item)).findFirst().orElse(null);
		System.out.println(product);
		product.findElement(By.xpath("//button[contains(text(),\" Add To Cart\")]")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-trigger-fadeIn"))));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));

		driver.findElement(By.xpath("//button[contains(text(),'Cart ')]")).click();
		
		
		List <WebElement> CartItem = driver.findElements(By.xpath("//div[@class='infoWrap']//h3"));
		boolean match = CartItem.stream().anyMatch(s->s.getText().equalsIgnoreCase(item));
		Assert.assertTrue(match);
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), country).build().perform();
		
		List<WebElement> noOfCountry = driver.findElements(By.xpath("//button[@type='button']"));
		
		
		 noOfCountry.stream().filter(s->s.getText().equals(country)).findFirst().orElse(null).click();
		driver.findElement(By.xpath("//a[contains(text(),'Place Order ')]")).click();
		
		String orderNum = driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText();
		System.out.println(orderNum);
		
		String confrmMsg = driver.findElement(By.className("hero-primary")).getText();
		
		System.out.println("conform msg is : "+confrmMsg);
		
		Assert.assertTrue(confrmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				

	}
	
}
