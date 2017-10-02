package com.sergey.prykhodko.model.users;

import com.sergey.prykhodko.exceptions.NotEnoughMoneyException;
import com.sergey.prykhodko.model.account.Account;
import com.sergey.prykhodko.model.services.Service;
import com.sergey.prykhodko.model.tariffplans.TariffPlan;
import com.sergey.prykhodko.services.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ClientTest {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client(LOGIN, PASSWORD);
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        client.setAccount(account);
    }

    @Test
    public void popUpAddsMoney() throws Exception {
        BigDecimal expectedNewBalance = BigDecimal.valueOf(1.000);

        client.popUpBalance(BigDecimal.valueOf(1.000));

        assertEquals(expectedNewBalance, client.checkBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNegativeAmount() throws Exception {

        client.popUpBalance(BigDecimal.valueOf(-10.222));
    }

    @Test
    public void payForServicesDecreasedClientsBalance() throws Exception {
        client.popUpBalance(BigDecimal.valueOf(50.000));
        TariffPlan tariffPlan = getTariffPlan50ChargePerMonth();
        client.setTariffPlan(tariffPlan);

        client.payAccordingTariff();

        assertEquals(BigDecimal.valueOf(0.000), client.checkBalance());
    }

    private TariffPlan getTariffPlan50ChargePerMonth() {
        TariffPlan tariffPlan = new TariffPlan();
        Service service = new Service();
        service.setChargePerMonth(BigDecimal.valueOf(50.00));
        tariffPlan.setServices(Arrays.asList(service));
        return tariffPlan;
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void notEnoughMoneyToPayForTariff() throws Exception {
        TariffPlan tariffPlan = getTariffPlan50ChargePerMonth();
        client.setTariffPlan(tariffPlan);

        client.payAccordingTariff();
    }

    @Test
    public void peyDecreasesAccountBalance() throws Exception {
        double someMoney = 50.000;
        final BigDecimal money = BigDecimal.valueOf(someMoney);
        client.popUpBalance(money);

        client.pay(money);

        assertEquals(BigDecimal.valueOf(0.000), client.checkBalance());
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void notEnoughMoneyToPay() throws Exception {
        double someMoney = 50.000;
        final BigDecimal money = BigDecimal.valueOf(someMoney);
        client.pay(money);
    }

    @Test(expected = IllegalArgumentException.class)
    public void payNegativeAmount() throws Exception {
        double someMoney = 20.000;
        final BigDecimal startMoney = BigDecimal.valueOf(someMoney);
        client.popUpBalance(startMoney);
        someMoney = -50.000;
        final BigDecimal money = BigDecimal.valueOf(someMoney);
        client.pay(money);
    }
}