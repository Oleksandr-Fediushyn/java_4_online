package ua.com.alevel;

import ua.com.alevel.controller.EmployeeController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new EmployeeController().start();
    }
}