package ru.appline.frameworks.rencredit.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static ru.appline.frameworks.rencredit.utils.NumbersUtil.cleanNumber;

public class ContributionsPage extends BasePage {

    @FindBy(xpath = "//label[@class = 'calculator__currency-field']")
    private List<WebElement> listRadioCurrency;

    @FindBy(xpath = "//label[text() = 'Открытие вклада']/../div[contains(@class, 'calculator__check-row-field')]")
    private List<WebElement> listRadioOpenDep;

    @FindBy(xpath = "//div[@class = 'calculator__content']//div[@class = 'calculator__check-row-field']")
    private List<WebElement> listCheckboxes;

    @FindBy(xpath = "//input[@name = 'amount']")
    private WebElement depositAmountInput;

    @FindBy(xpath = "//select[@name = 'period']")
    private WebElement selectPeriod;

    @FindBy(xpath = "//input[@name = 'replenish']")
    private WebElement replenishInput;

    @FindBy(xpath = "//div[@class = 'calculator__result-block']")
    private WebElement resultBlock;

    private void setCheckbox(WebElement checkbox, boolean checked){
        if (checked){
            if (!Boolean.valueOf(checkbox.getAttribute("checked"))) {
                WebElement toWithdrawElem = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-result']"));
                String beforeValue = toWithdrawElem.getText();
                action.click(checkbox).build().perform();
                Assert.assertTrue("Проверка на переключение чекбокса '" + checkbox.findElement(By.xpath("./../../..//span[@class = 'calculator__check-text']")).getText() + "' не пройдена", elementToBeChanged(toWithdrawElem , "textContent", beforeValue));
            }
        }
        else {
            if (Boolean.valueOf(checkbox.getAttribute("checked"))) {
                WebElement toWithdrawElem = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-result']"));
                String beforeValue = toWithdrawElem.getText();
                action.click(checkbox).build().perform();
                Assert.assertTrue("Проверка на переключение чекбокса '" + checkbox.findElement(By.xpath("./../../..//span[@class = 'calculator__check-text']")).getText() + "' не пройдена", elementToBeChanged(toWithdrawElem , "textContent", beforeValue));
            }
        }
    }

    private void fillInputField(WebElement field, String valueField){
        WebElement toWithdrawElem = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-result']"));
        String beforeValue = toWithdrawElem.getText();
        elementToBeClickable(field);
        field.click();
        field.sendKeys(valueField);
        elementToBeChanged(toWithdrawElem , "textContent", beforeValue);
    }

    @Step("Выбор радиобаттона валюты ${currency}")
    public ContributionsPage setCurrency(String currency){
        WebElement inputCurrency;
        for (WebElement labelCurrency : listRadioCurrency){
            inputCurrency = labelCurrency.findElement(By.xpath("./input"));
            if (inputCurrency.getAttribute("value").equalsIgnoreCase(currency)){
                action.click(inputCurrency).build().perform();
                Assert.assertTrue("Валюта '" + currency + "' не выбрана", inputCurrency.isSelected());
                return this;
            }
        }
        Assert.fail("Указанной валюты '" + currency + "' в форме по расчету доходности по вкладу не существует.");
        return this;
    }

    @Step("Заполнение поля ${nameField} значением ${valueField}")
    public ContributionsPage fillField(String nameField, String valueField){
        WebElement element = null;
        switch (nameField) {
            case "Сумма вклада":
                fillInputField(depositAmountInput, valueField);
                element = depositAmountInput;
                break;
            case "Ежемесячное пополнение":
                fillInputField(replenishInput, valueField);
                element = replenishInput;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице 'Вклады'");
        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                cleanNumber(valueField), cleanNumber(element.getAttribute("value")));
        return this;
    }

    @Step("Выбор периода вклада ${period}")
    public ContributionsPage selectPeriod(String period){
        WebElement toWithdrawElem = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-result']"));
        String beforeValue = toWithdrawElem.getText();
        Select guarantSelect = new Select(selectPeriod);
        guarantSelect.selectByVisibleText(period);
        elementToBeChanged(toWithdrawElem, "textContent", beforeValue);
        return this;
    }

    @Step("Переключение радиобаттона {nameRadio}")
    public ContributionsPage selectRadioButton(String nameRadio){
        WebElement inputName, inputElement;
        for (WebElement blockRadioOpenDep : listRadioOpenDep){
            inputName = blockRadioOpenDep.findElement(By.xpath(".//span[@class = 'calculator__check-text']"));
            if (inputName.getText().equalsIgnoreCase(nameRadio)){
                inputElement = blockRadioOpenDep.findElement(By.xpath(".//input"));
                action.click(inputElement).build().perform();
                Assert.assertTrue("Радиобаттон '" + nameRadio + "' не выбрана", inputElement.isSelected());
                return this;
            }
        }
        Assert.fail("Указанного радиобаттона '" + nameRadio + "' в форме по расчету доходности по вкладу не существует.");
        return this;
    }

    @Step("Переключение чекбокса {nameCheckbox} на значение {checked}")
    public ContributionsPage checkboxIs(String nameCheckbox, boolean checked){
        WebElement element = null;
        for (WebElement checkboxCalc : listCheckboxes){
            if (checkboxCalc.findElement(By.xpath(".//span[@class = 'calculator__check-text']")).getText().equalsIgnoreCase(nameCheckbox)){
                WebElement checkMonthlyCapit = checkboxCalc.findElement(By.xpath(".//span[contains(text(), '" + nameCheckbox + "')]/../..//input"));
                setCheckbox(checkMonthlyCapit, checked);
                element = checkMonthlyCapit;
                break;
            }
            Assert.fail("Чекбокс '" + nameCheckbox + "' отсутствует на странице 'Вклады'");

        }
        Assert.assertEquals("Чекбокс '" + nameCheckbox + "' был установлен некорректно",
                checked, Boolean.valueOf(element.getAttribute("checked")));
        return this;
    }

    @Step("Проверка результата {nameCheckResult} на значение {valueCheckResult}")
    public ContributionsPage checkResult(String nameCheckResult, String valueCheckResult){
        WebElement element = null;
        switch (nameCheckResult) {
            case "Начислено":
                element = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-earned']"));
                break;
            case "Пополнение":
                element = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-replenish']"));
                break;
            case "К снятию":
                element = resultBlock.findElement(By.xpath(".//span[@class = 'js-calc-result']"));
                break;
            default:
                Assert.fail("Результат '" + nameCheckResult + "' отсутствует на странице 'Вклады'");
        }
        Assert.assertEquals("Результат '" + nameCheckResult + "' был вычислен некорректно",
                cleanNumber(valueCheckResult), cleanNumber(element.getText()));
        return this;
    }

}
