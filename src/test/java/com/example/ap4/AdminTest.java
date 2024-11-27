package com.example.ap4;

import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminTest {

    @Before
    public void signSet() {

        String name = "Admin";
        String email = "Admin@123";
        String password = "admin123";
        OrderManager orderManager=new OrderManager();
        ByteMe.adminCredentials.put(email,password);

    }


    @Test
    public void IncorrectLogin() throws Exception {

        String email = "aks@id.com";
        String password = "aks@123";

        String s = tapSystemOut(() -> {
            int status = ByteMe.admin_login(email, password);

            assertEquals(-1, status);
        });
        assertTrue(s.contains("Invalid Credentials. Please try again."));
    }

    @Test
    public void CorrectLogin() throws Exception {

        String email = "Admin@123";
        String password = "admin123";

        String s = tapSystemOut(() -> {
            int status = ByteMe.admin_login(email, password);

            assertEquals(1, status);
        });

        assertTrue(s.contains("Admin login successful!"));
    }


}