package ru.appline.frameworks.sberbank.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.frameworks.sberbank.managers.ManagerPages;
import ru.appline.frameworks.sberbank.managers.TestPropManager;

import static ru.appline.frameworks.sberbank.managers.DriverManager.getDriver;

public class BasePage {

    @FindBy(xpath = "//div[@data-test-id='main-results-block']")
    private WebElement resultBlock;

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

    public String cleanNumber(String priceString){
        String price = "";
        String numbers = "0987654321.,";
        for (int i = 0; i < priceString.length(); ++i) {
            if (numbers.contains("" + priceString.charAt(i))) {
                price += priceString.charAt(i);
            }
        }
        return price;
    }

    public WebElement check(String nameCheck, String valueCheck){
        WebElement element = resultBlock.findElement(By.xpath(".//span[text() = '" + nameCheck + "']/../span/span"));
        Assert.assertEquals("Проверка на '" + nameCheck + "' не пройдена", valueCheck, cleanNumber(element.getText()));
        return element;
    }

    public void setCheckbox(WebElement checkbox, boolean checked){
        if (checked){
            if (checkbox.getAttribute("ariaChecked").equals("false")) {
                String beforeValue = resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")).getAttribute("textContent");
                checkbox.click();
                Assert.assertTrue("Проверка на переключение чекбокса '" + checkbox.findElement(By.xpath("./../../../../span")) + "' не пройдена", elementToBeChanged(resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")),"textContent", beforeValue));
            }
        }
        else {
            if (checkbox.getAttribute("ariaChecked").equals("true")) {
                String beforeValue = resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")).getAttribute("textContent");
                checkbox.click();
                Assert.assertTrue("Проверка на переключение чекбокса '" + checkbox.findElement(By.xpath("./../../../../span")) + "' не пройдена", elementToBeChanged(resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")),"textContent", beforeValue));
            }
        }
    }

    public void fillInputField(WebElement field, String value) {
        String beforeValue = resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")).getAttribute("textContent");
        elementToBeClickable(field);
        action.doubleClick(field).build().perform();
        js.executeScript("arguments[0].value='';", field);
        field.sendKeys(value);
        elementToBeChanged(resultBlock.findElement(By.xpath(".//span[text() = 'Ежемесячный платеж']/../span/span")) , "textContent", beforeValue);
    }

    public void switchToFrame(){
        getDriver().switchTo().frame("iFrameResizer0");
    }

    public void jsClear(WebElement element){
        js.executeScript("arguments[0].value='';", element);
    }

    public String numbersToWithoutSpace(String number){
        String numberAfter = "";
        String numbers = "0987654321";
        for (int i = 0; i < number.length(); i++) {
            if (numbers.contains("" + number.charAt(i))) {
                numberAfter += number.charAt(i);
            }
        }
        return numberAfter;
    }

    public String numbersToWithSpace(String number){
        String numberAfter = "", buf;
        for (int i = number.length() - 1, n = 1; i >= 0; i--, n++) {

            buf = numberAfter;
            if (n % 3 == 0){
                numberAfter = " " + number.charAt(i) + buf;
            }
            else {
                numberAfter = number.charAt(i) + buf;
            }

        }
        return numberAfter;
    }

}
