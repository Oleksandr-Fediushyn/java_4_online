package ua.com.alevel;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input text for analysis");
        System.out.println();
        String userText = reader.readLine();
        System.out.println("Look the results of text analysis, please");
        System.out.println();
        PrintTableTest printTable = new PrintTableTest(userText);
        printTable.PrintTableInConsole();

    }
}