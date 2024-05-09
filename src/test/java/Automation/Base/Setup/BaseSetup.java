package Automation.Base.Setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseSetup {
    private static WebDriver driver;
    private final int PageLoadTimeOut = 30;
    private final int ImplicitWaitTimeOut = 20;

    public static WebDriver getdriver() {
        return driver;
    }
    public WebDriver setupdriver (String browserType)
    {
        switch (browserType.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }
    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                driver.navigate().to(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver();
                driver.navigate().to(appURL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
    }
    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
       // System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PageLoadTimeOut,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ImplicitWaitTimeOut, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        //System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PageLoadTimeOut,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ImplicitWaitTimeOut, TimeUnit.SECONDS);
        return driver;
    }
    @Parameters({ "browserType", "appURL" })
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String appURL) {
        try {
            // Khởi tạo driver và browser
            setDriver(browserType, appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
