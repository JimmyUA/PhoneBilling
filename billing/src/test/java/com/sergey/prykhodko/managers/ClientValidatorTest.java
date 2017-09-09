package com.sergey.prykhodko.managers;

import com.sergey.prykhodko.users.Client;
import com.sergey.prykhodko.users.ClientBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
@Ignore
public class ClientValidatorTest {

    Client validClient;

    @Before
    public void setUp() throws Exception {
        validClient = new ClientBuilder()
                .setFullName("Test User")
                .setEmail("test@test.test")
                .setLogin("login")
                .setPassword("RRRRRRR7")
                .setActive(false)
                .build();
    }

    @Test
    public void passwordNotMatches() throws Exception {
        String shortPassword = "Aghdf4";
        String noDigitPassword = "Aaaaaaaaa";

        validClient.setPassword(shortPassword);
        assertFalse(new ClientValidator().validate(validClient));

        validClient.setPassword(noDigitPassword);
        assertFalse(new ClientValidator().validate(validClient));
    }

    @Test
    public void passwordMatches() throws Exception {
        String correctPassword = "JimmyLee88";

        validClient.setPassword(correctPassword);
        assertTrue(new ClientValidator().validate(validClient));
    }

    @Ignore@Test
    public void notUniqueLogin() throws Exception {
        //Todo make with mock DAO
    }

    @Test
    public void emailNotMatchTemplate() throws Exception {
        String nonDogEmail = "test.test.test";
        String notCorrectDomainFormEmail = "test@test";

        validClient.setEmail(nonDogEmail);
        assertFalse(new ClientValidator().validate(validClient));

        validClient.setPassword(notCorrectDomainFormEmail);
        assertFalse(new ClientValidator().validate(validClient));
    }

    @Test
    public void emailMatches() throws Exception {
        String correctEmail = "Jimmy.Lee@mail.ru";

        validClient.setEmail(correctEmail);
        assertTrue(new ClientValidator().validate(validClient));
    }

    @Test
    public void fullNameOneWord() throws Exception {
        String oneWordFullName = "Bob";
        validClient.setFullName(oneWordFullName);

        assertFalse(new ClientValidator().validate(validClient));
    }

    @Test
    public void notActive() throws Exception {
        validClient.setActive(true);

        assertFalse(new ClientValidator().validate(validClient));
    }
}