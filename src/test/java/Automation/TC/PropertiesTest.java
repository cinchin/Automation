package Automation.TC;
import Automation.Base.Setup.BaseSetup;
import Automation.Base.ExcelandProperties.ResolveProperties;
import Automation.Pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertiesTest {
    private WebDriver driver;
    private SignInPage SignInPage;

    @BeforeClass
    public void setup() {
        ResolveProperties.setPropertiesFile();
        driver = new BaseSetup().setupdriver(ResolveProperties.getPropValue("browser"));
    }

    @Test (priority = 0)
    public void Properties1() throws Exception {
        SignInPage = new SignInPage(driver);
        driver.get("https://practice.automationtesting.in/my-account/");
        SignInPage.SignIn(ResolveProperties.getPropValue("email"),ResolveProperties.getPropValue("password"));
        //SignInPage.Error();
        Thread.sleep(3000);
    }
    @AfterClass
    public void quitbrowser()
    {
        driver.close();
    }
}
