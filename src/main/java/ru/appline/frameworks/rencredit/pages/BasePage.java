package ru.appline.frameworks.rencredit.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.frameworks.rencredit.managers.ManagerPages;
import ru.appline.frameworks.rencredit.managers.TestPropManager;

import static ru.appline.frameworks.rencredit.managers.DriverManager.getDriver;

public class BasePage {

    protected ManagerPages app = ManagerPages.getManagerPages();
    protected Actions action = new Actions(getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);
    public static TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean elementToBeChanged(WebElement element, String attribute, String beforeValue) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(element, attribute, beforeValue)));
    }

}
