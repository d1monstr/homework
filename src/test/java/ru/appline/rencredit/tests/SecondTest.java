package ru.appline.rencredit.tests;

import org.junit.Test;
import ru.appline.rencredit.base.BaseTests;

public class SecondTest extends BaseTests {

    @Test
    public void test(){

        app.getStartPage()
                .selectContributions("Вклады")
                .setCurrency("USD")
                .fillField("Сумма вклада", "500000")
                .selectPeriod("9 месяцев")
                .fillField("Ежемесячное пополнение", "25000")
                .checkboxIs("Ежемесячная капитализация", true)
                .checkResult("Начислено", "668,53")
                .checkResult("Пополнение", "200000")
                .checkResult("К снятию", "700668,53");

    }

}