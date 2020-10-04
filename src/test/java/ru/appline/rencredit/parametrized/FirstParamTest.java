package ru.appline.rencredit.parametrized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.appline.rencredit.base.BaseTests;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FirstParamTest extends BaseTests {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"RUB", "300000", "6 месяцев", "50000", true, "8704,31", "250000", "558704,31"},
                {"USD", "500000", "9 месяцев", "25000", true, "668,53", "200000", "700668,53"}
        });
    }

    @Parameterized.Parameter
    public String currency;

    @Parameterized.Parameter(1)
    public String amountDeposit;

    @Parameterized.Parameter(2)
    public String period;

    @Parameterized.Parameter(3)
    public String monthlyWithdraw;

    @Parameterized.Parameter(4)
    public boolean monthlyCapit;

    @Parameterized.Parameter(5)
    public String accrued;

    @Parameterized.Parameter(6)
    public String replenishment;

    @Parameterized.Parameter(7)
    public String toWithdraw;

    @Test
    public void test(){

        app.getStartPage()
                .selectContributions("Вклады")
                .setCurrency(currency)
                .fillField("Сумма вклада", amountDeposit)
                .selectPeriod(period)
                .fillField("Ежемесячное пополнение", monthlyWithdraw)
                .checkboxIs("Ежемесячная капитализация", monthlyCapit)
                .checkResult("Начислено", accrued)
                .checkResult("Пополнение", replenishment)
                .checkResult("К снятию", toWithdraw);

    }

}
