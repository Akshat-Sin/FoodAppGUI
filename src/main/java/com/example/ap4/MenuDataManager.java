package com.example.ap4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MenuDataManager {
    private static final String MENU_FILE = "menu_data.txt";

    public static void writeMenuToFile(Map<String, MenuItem> menu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : menu.values()) {
                writer.write(item.getName() + "," + item.getPrice() + "," + item.getCategory() + "," + item.getAvailability());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, MenuItem> readMenuFromFile() {
        Map<String, MenuItem> menu = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                menu.put(data[0], new MenuItem(data[0], (int) Double.parseDouble(data[1]), data[2], Integer.parseInt(data[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menu;
    }
}
