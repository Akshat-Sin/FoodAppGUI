package com.example.ap4;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemTest {
    @Before
    public void setup(){
        MenuItem newItem1 = new MenuItem("Biryani", 75, "Meals", 0);
        OrderManager.getMenu().put("Biryani", newItem1);

        MenuItem newItem2 = new MenuItem("HoneyChilliPotato", 45, "Snacks", 100);
        OrderManager.getMenu().put("HoneyChilliPotato", newItem2);
    }

    @Test
    public void OutOfStock() throws Exception {
        Scanner scanner=mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(5);
        Customer customer=new Customer("a","b","c",new OrderManager());

        String output = tapSystemOut(() -> {
            int result = customer.addItem("Biryani",scanner);

            assertEquals(0, result);
        });

        assertTrue(output.contains("Out of stock."));
    }

    @Test
    public void InStock() throws Exception {
        Scanner scanner=mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(5);
        Customer customer=new Customer("a","b","c",new OrderManager());

        String output = tapSystemOut(() -> {
            int result = customer.addItem("HoneyChilliPotato",scanner);

            assertEquals(1, result);
        });

        assertTrue(output.contains("Item added to cart."));
    }

    @Test
    public void ItemNotExist() throws Exception {
        Scanner scanner=mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(5);
        Customer customer=new Customer("a","b","c",new OrderManager());

        String output = tapSystemOut(() -> {
            int result = customer.addItem("ChickenRoll",scanner);

            assertEquals(-1, result);
        });

        assertTrue(output.contains("Item not found."));
    }
}