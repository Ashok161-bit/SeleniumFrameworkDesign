package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import PageObjectiveModel.LoginPage;

public class BaseTest {
    public WebDriver driver; // Static driver
    public LoginPage loginPage;
    protected String email = "ashokpinninti77@gmail.com";
    protected String password = "Rahul@#123";

    public WebDriver initializeDriver() throws IOException {
        if (driver == null) { // Initialize only if driver is null
            Properties prop = new Properties();
            
            FileInputStream fis = new FileInputStream(
            System.getProperty("user.dir") + "/src/main/java/Resources/GlobalData.properties");
            prop.load(fis);
            
           // String browserName = prop.getProperty("browser");
            String browserName = System.getProperty("browser");
            if (browserName == null) {
                browserName = prop.getProperty("browser");
            }


            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }
    
    @BeforeMethod
    public LoginPage LaunchApplication() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        return loginPage;
    }

    
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // Read JSON to String
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\ComponentData\\purchase.json"));

        // Convert String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		// Implement screenshot capture logic here
		// For example, using Selenium's TakesScreenshot interface
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
	}
    
    @AfterMethod
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	        driver = null; 
	    }
	}

	
}
