package Automation.TC;

import Automation.Base.Setup.BaseSetup;
import Automation.Base.Log.Log;
import Automation.Base.Setup.ValidateHelper;
import Automation.Pages.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SignInTest extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper ValidateHelper;
    private SignInPage SignInPage;

    @BeforeClass
    public void setup() {
        driver = getdriver();
        ValidateHelper = new ValidateHelper(driver);
    }

    @Test (priority = 0)
    public void SignInPage() throws InterruptedException {
        Log.info("test");
        SignInPage = new SignInPage(driver);
        SignInPage.Reg("abc123@email.com", "abcxyz123A!aa");
        SignInPage.Error();
        Thread.sleep(3000);
    }
    @Test (priority = 1)
    public void SignInPage1() throws InterruptedException {
        SignInPage = new SignInPage(driver);
        SignInPage.Reg("abc123@email.com", "123456");
        SignInPage.Error();
        Thread.sleep(3000);
    }
    @Test (priority = 2)
    public void SignInPage2() throws InterruptedException {
        SignInPage = new SignInPage(driver);
        SignInPage.Reg("abc123@email.com", "123456abcAb!");
        SignInPage.Error();
        Thread.sleep(3000);
    }
    @Test (priority = 3)
    public void SignInPage3() throws InterruptedException {
        SignInPage = new SignInPage(driver);
        SignInPage.Reg("", "abcxyz123A!aa");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        SignInPage.Invalid();
    }
    @Test (priority = 4)
    public void SignInPage4() throws InterruptedException {
        SignInPage = new SignInPage(driver);
        SignInPage.Reg("abc123@email.com", "");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        SignInPage.Invalid();
    }

    @AfterClass
    public void quitbrowser()
    {
        driver.close();
    }
}
