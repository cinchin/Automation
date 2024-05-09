package Automation.Pages;

import Automation.Base.Setup.ValidateHelper;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    private ValidateHelper ValidateHelper;
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        ValidateHelper = new ValidateHelper(driver);
    }
}
