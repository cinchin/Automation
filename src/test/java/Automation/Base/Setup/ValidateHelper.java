package Automation.Base.Setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidateHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Select select;
    private Actions action;
    public final int timeoutWaitForPageLoaded = 20;
    public final int timeoutWait = 10;

    JavascriptExecutor js;

    public ValidateHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    //Get Page Title
    public String getpageTitle() {
        WaitForPageLoaded();
        String Title = driver.getTitle();
        return Title;
    }

    //Verify Page Title
    public boolean VerifyPageTitle(String pagetitle) {
        WaitForPageLoaded();
        return getpageTitle().equals(pagetitle);
    }

    //Verify Page URL
    public boolean VerifyPageURL(String URL) {
        System.out.println(driver.getCurrentUrl());
        System.out.println(URL);
        return driver.getCurrentUrl().contains(URL);
    }

    //Verify button
    public boolean VerifyButton(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isEnabled();
    }

    //sendkeys một giá trị
    public void setText(By element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    //click vào một phần tử element
    public void ClickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    //click vào một phần tử element by js
    public void ClickElementJS(By element) {
        WaitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    //Selectoption
    public void selectText(By element, String text) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    //Verify optiontotal
    public void verifytotal(By element, int total) {
        select = new Select(driver.findElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }

    // page loaded
    public void WaitForPageLoaded() {
        ExpectedCondition<Boolean> expectation1 = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> expectation2 = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expectation1);
            wait.until(expectation2);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load request");
        }
    }
}

