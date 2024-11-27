package com.example.ap4;

import java.util.Scanner;

public interface Customer_interface {
    String getPassword();

    boolean isVIP();

    void setVIP(boolean VIP);

    void customerInterface(Scanner scanner) throws NonIntegerInputException;
}
