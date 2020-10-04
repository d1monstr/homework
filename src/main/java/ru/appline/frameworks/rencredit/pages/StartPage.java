package ru.appline.frameworks.rencredit.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement titleElement;

    @FindBy(xpath = "//div[@class = 'service']")
    private List<WebElement> listServices;

    @Step("Выбор вкладки сервиса ${serviceName}")
    public ContributionsPage selectContributions(String serviceName){
        WebElement titleService, serviceButton;
        for (WebElement serviceElement : listServices){
            titleService = serviceElement.findElement(By.xpath(".//div[@class = 'service__title-text']"));
            serviceButton = titleService.findElement(By.xpath("./../a[1]"));
            if (titleService.getText().equalsIgnoreCase(serviceName)) {
                elementToBeClickable(serviceButton);
                serviceButton.click();
                Assert.assertEquals("Проверка на соответствие заголовка страницы", serviceName, titleElement.getText());
                return app.getContributionsPage();
            }
        }
        Assert.fail("Сервиса под названием '" + serviceName + "' на странице не существует.");
        return app.getContributionsPage();
    }

    @Step("Выбор вкладки сервиса ${serviceName}")
    public LoansPage selectLoans(String serviceName){
        WebElement titleService, serviceButton;
        for (WebElement serviceElement : listServices){
            titleService = serviceElement.findElement(By.xpath(".//div[@class = 'service__title-text']"));
            serviceButton = titleService.findElement(By.xpath("./../a[1]"));
            if (titleService.getText().equalsIgnoreCase(serviceName)) {
                elementToBeClickable(serviceButton);
                serviceButton.click();
                Assert.assertEquals("Проверка на соответствие заголовка страницы", "Кредит наличными", titleElement.getText());
                return app.getLoansPage();
            }
        }
        Assert.fail("Сервиса под названием '" + serviceName + "' на странице не существует.");
        return app.getLoansPage();
    }

    @Step("Выбор вкладки сервиса ${serviceName}")
    public CardsPage selectCards(String serviceName){
        WebElement titleService, serviceButton;
        for (WebElement serviceElement : listServices){
            titleService = serviceElement.findElement(By.xpath(".//div[@class = 'service__title-text']"));
            serviceButton = titleService.findElement(By.xpath("./../a[1]"));
            if (titleService.getText().equalsIgnoreCase(serviceName)) {
                elementToBeClickable(serviceButton);
                serviceButton.click();
                Assert.assertEquals("Проверка на соответствие заголовка страницы", serviceName, titleElement.getText());
                return app.getCardsPage();
            }
        }
        Assert.fail("Сервиса под названием '" + serviceName + "' на странице не существует.");
        return app.getCardsPage();
    }




}
