package ru.appline.rencredit.tests;

import org.junit.Test;
import ru.appline.rencredit.base.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void test(){

        app.getStartPage()
                .selectContributions("Вклады")
                .setCurrency("RUB")
                .fillField("Сумма вклада", "300000")
                .selectPeriod("6 месяцев")
                .fillField("Ежемесячное пополнение", "50000")
                .checkboxIs("Ежемесячная капитализация", true)
                .checkResult("Начислено", "8704,31")
                .checkResult("Пополнение", "250000")
                .checkResult("К снятию", "558704,31");

    }

}
