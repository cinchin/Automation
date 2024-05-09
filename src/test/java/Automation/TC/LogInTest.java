package Automation.TC;

import Automation.Base.Setup.BaseSetup;
import Automation.Base.ExcelandProperties.ResolveExcel;
import Automation.Base.Log.Log;
import Automation.Base.ReportListener.Listener;
import Automation.Base.Setup.ValidateHelper;
import Automation.Pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(Listener.class)
public class LogInTest {
    private WebDriver driver;
    private ValidateHelper ValidateHelper;
    private SignInPage SignInPage;
    private ResolveExcel Excel;

    @BeforeClass
    public void setup() {
        driver = new BaseSetup().setupdriver("chrome");
        ValidateHelper = new ValidateHelper(driver);
        Excel = new ResolveExcel();
    }

    @Test ()
    public void SignInPage1() throws Exception {
        Log.info("test for signin");
        Excel.setExcelFile("src/test/resources/User.xlsx","Sheet1");
        SignInPage = new SignInPage(driver);
        driver.get("https://practice.automationtesting.in/my-account/");
        SignInPage.SignIn(Excel.getCellData("email",1), Excel.getCellData("password",1));
        //SignInPage.Error();
        Thread.sleep(3000);
    }

@AfterClass
public void quitbrowser()
{
    driver.close();
}
}