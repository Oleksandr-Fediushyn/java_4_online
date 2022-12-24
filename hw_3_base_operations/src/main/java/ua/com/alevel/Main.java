package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TASK 1");
        System.out.println();
        System.out.println("Please, input string: ");
        String inputString = reader.readLine();
        System.out.println("Original string: " + inputString);
        String sortDigits = inputString.replaceAll("[\\D]", "");
        System.out.println("Only digits: " + sortDigits);
        int sum = 0;
        for (int i = 0; i < sortDigits.length(); ) {
            sum += Integer.parseInt(sortDigits.substring(i, ++i));
        }
        System.out.println("Sum of digits: " + sum);
        System.out.println();

        System.out.println("TASK 2");
        System.out.println();
        System.out.println("Please, input string: ");
        String inputStringNew = reader.readLine();
        System.out.println("Original string: " + inputStringNew);
        String latinText = inputStringNew.replaceAll("[^a-zA-Z]", "");
        System.out.println("Only latin characters: " + latinText);
        char tempArray[] = latinText.toCharArray();
        Arrays.sort(tempArray);
        String sortLatinText = new String(tempArray);
        System.out.println("Sorting string: " + sortLatinText);

        char ch = sortLatinText.charAt(0);
        int num = 1;
        int count = 1;
        System.out.println("Result:");
        for (int i = 1; i < sortLatinText.length();i++) {
            if (sortLatinText.charAt(i) == ch)
                count++;
            else {
                System.out.print(num + ".");
                System.out.print(ch + "-");
                System.out.print(count + " ");
                ch = sortLatinText.charAt(i);
                count = 1;
                num++;
            }

        }
        System.out.print(num + ".");
        System.out.print(ch + "-");
        System.out.print(count + " ");
        System.out.println();
        System.out.println();

        System.out.println("TASK 3");
        System.out.println();
        System.out.println("Please, input number of the lesson: ");
        int lessonNumber = Integer.parseInt(reader.readLine());
        System.out.println("Number of the lesson: " + lessonNumber);
        int lessonTime = 45;
        int oddBreak = 5;
        int evenBreak = 15;
        int timeStart = 9 * 60;
        int endLessonTime = timeStart + lessonTime * lessonNumber;
        endLessonTime += (lessonNumber / 2) * oddBreak;
        endLessonTime += ((lessonNumber + 1) / 2 - 1) * evenBreak;
        System.out.println("End of the lesson number " + lessonNumber + ": " + endLessonTime / 60 + " " + endLessonTime % 60);

    }

}