package Automation.Pages;

import Automation.Base.Setup.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    WebDriver driver;
    private ValidateHelper ValidateHelper;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        ValidateHelper = new ValidateHelper(driver);
    }

    //Element Resigter
    private By RegEmail = By.id("reg_email");
    private By RegPass = By.id("reg_password");
    private By RegBtn = By.name("register");
    private By LogEmailorUser = By.id("username");
    private By LogPass = By.id("password");
    private By LogBtn = By.xpath("//input[@name='login']");
    private By url = By.xpath("//a[normalize-space()='My Account']");
    private By error = By.cssSelector("div[aria-live='polite']");
    private By Invalid = By.xpath("//ul[@class='woocommerce-error']");
    private By remember = By.id("rememberme");

    //register
    public void Reg(String email, String pass) throws InterruptedException {
        driver.findElement(url).click();
        driver.findElement(RegEmail).sendKeys(email);
        Thread.sleep(2000);
        driver.findElement(RegPass).sendKeys(pass);
        ValidateHelper.VerifyButton(RegBtn);
        if (ValidateHelper.VerifyButton(RegBtn)) {
            System.out.println("Enabled");
        } else {
            System.out.println("Disabled");
        }
    }

    public void Error() {
        System.out.println(driver.findElement(error).getText());
    }

    public void Invalid() {
        System.out.println(driver.findElement(Invalid).getText());
    }

    //SignIn
    public void SignIn(String email, String pass) {
        driver.findElement(url).click();
        driver.findElement(LogEmailorUser).sendKeys(email);
        driver.findElement(LogPass).sendKeys(pass);
        driver.findElement(remember).click();
        driver.findElement(LogBtn).click();
    }


}
