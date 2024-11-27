package com.example.ap4;

import org.junit.Before;
import org.junit.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    @Before
    public void signSet() {

        String name = "Akshat";
        String email = "aks@id.com";
        String password = "aks123";
        OrderManager orderManager=new OrderManager();
        ByteMe.customerCredentials.put(email,new Customer(name,email,password,orderManager));

    }

    @Test
    public void IncorrectEmail() throws Exception {

        String email = "aksid.com";
        String password = "aks123";

        String s = tapSystemOut(() -> {
            int status = ByteMe.customerLogin(email, password);

            assertEquals(0, status);
        });

        assertTrue(s.contains("You are not signed in. Please try again"));
    }

    @Test
    public void IncorrectPassword() throws Exception {

        String email = "aks@id.com";
        String password = "aks@123";

        String s = tapSystemOut(() -> {
            int status = ByteMe.customerLogin(email, password);

            assertEquals(-1, status);
        });

        assertTrue(s.contains("Invalid credentials. Please try again."));
    }

    @Test
    public void CorrectLogin() throws Exception {

        String email = "aks@id.com";
        String password = "aks123";

        String s = tapSystemOut(() -> {
            int status = ByteMe.customerLogin(email, password);

            assertEquals(1, status);
        });

        assertTrue(s.contains("Customer login successful!"));
    }


}