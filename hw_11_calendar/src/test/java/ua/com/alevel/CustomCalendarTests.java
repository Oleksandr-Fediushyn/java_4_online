package ua.com.alevel;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.After;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CustomCalendarTests {

    private static int COUNT = 0;
    private static final int SIZE = 10;

    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    public CustomCalendarTests() {
    }


    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < SIZE; i++) {
            CustomCalendar calendar = new CustomCalendar();
            COUNT++;
        }
    }


    @Test
    @Order(1)
    public void checkCreateCalendarObject() {
        Assertions.assertEquals(COUNT, SIZE);
    }

    @Test
    @Order(2)
    public void checkCreateCalendarObjectInStringFormatWithCorrectData() {
        String newDate = "2023-03-10 23:23:12 456";
        CustomCalendar newCalendar = new CustomCalendar(newDate);
        CustomCalendar testCalendar = new CustomCalendar();
        testCalendar.set(newDate);
        Assertions.assertEquals(newCalendar.toString(), testCalendar.toString());
    }

    @Test
    @Order(3)
    public void checkCreateCalendarObjectInStringFormatWithNoCorrectData() {
        String newDate = "2023-13-10 24:23:12 456";
        CustomCalendar newCalendar = new CustomCalendar(newDate);
        Assertions.assertEquals(newCalendar.toString(), "0000-00-00 00:00:00 000");
    }

    @Test
    @Order(4)
    public void checkGetMillisecondsCorrectTransform() {
        String newDate = "2023-02-10 23:23:12 456";
        CustomCalendar newCalendar = new CustomCalendar(newDate);
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(newDate);
        Assertions.assertEquals(newCalendar.getTimeMillis(), testCalendar.getTimeMillis());
    }

    @Test
    @Order(5)
    public void checkSetYearMonth() {
        String newDate = "2022-02-10 23:23:12 456";
        String newValue = "2023-11-10 23:23:12 456";
        int year = 2023;
        int month = 11;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month);
        Assertions.assertEquals(testCalendar.toString(), newValue);
    }

    @Test
    @Order(6)
    public void checkSetYearMonthNotCorrect() {
        String newDate = "2022-02-10 23:23:12 456";
        int year = 2023;
        int month = 13;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month);
        Assertions.assertEquals(testCalendar.toString(), newDate);
    }

    @Test
    @Order(7)
    public void checkSetYearMonthDay() {
        String newDate = "2022-02-10 20:23:12 456";
        String newValue = "2023-11-11 23:27:12 456";
        int year = 2023;
        int month = 11;
        int day = 11;
        int hour = 23;
        int minutes = 27;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month, day, hour, minutes);
        Assertions.assertEquals(testCalendar.toString(), newValue);
    }

    @Test
    @Order(8)
    public void checkSetYearMonthDayNotCorrect() {
        String newDate = "2022-02-10 23:23:12 456";
        int year = 2023;
        int month = 11;
        int day = 11;
        int hour = 25;
        int minutes = 27;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month, day, hour, minutes);
        Assertions.assertEquals(testCalendar.toString(), newDate);
    }

    @Test
    @Order(9)
    public void checkSetYearMonthDayMilliseconds() {
        String newDate = "2022-02-10 20:23:12 456";
        String newValue = "2023-11-11 23:27:45 346";
        int year = 2023;
        int month = 11;
        int day = 11;
        int hour = 23;
        int minutes = 27;
        int seconds = 45;
        int milliseconds = 346;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month, day, hour, minutes, seconds, milliseconds);
        Assertions.assertEquals(testCalendar.toString(), newValue);
    }

    @Test
    @Order(10)
    public void checkSetYearMonthDayMillisecondsNotCorrect() {
        String newDate = "2022-02-10 23:23:12 456";
        int year = 2023;
        int month = 11;
        int day = 11;
        int hour = 25;
        int minutes = 27;
        int seconds = 70;
        int milliseconds = 346;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.set(year, month, day, hour, minutes, seconds, milliseconds);
        Assertions.assertEquals(testCalendar.toString(), newDate);
    }

    @Test
    @Order(11)
    public void checkSetDataInTextFormat() {
        String newDate = "2022-02-10 20:23:12 456";
        CustomCalendar testCalendar = new CustomCalendar();
        testCalendar.set(newDate);
        Assertions.assertEquals(testCalendar.toString(), newDate);
    }

    @Test
    @Order(12)
    public void checkSetDataInTextFormatNotCorrect() {
        String newDate = "2022-02-10 24:23:12 456";
        CustomCalendar testCalendar = new CustomCalendar();
        String oldValue = testCalendar.toString();
        testCalendar.set(newDate);
        Assertions.assertEquals(testCalendar.toString(), oldValue);
    }

    @Test
    @Order(13)
    public void checkAddDataToObject() {
        String newDate = "1900-02-10 20:23:12 456";
        String addDate = "0001-02-10 04:00:12 200";
        String resultDate = "1901-03-24 00:23:24 656";
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        CustomCalendar newCalendar = new CustomCalendar(addDate);
        testCalendar.addDate(newCalendar);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(14)
    public void checkAddDataInStringFormat() {
        String newDate = "1900-02-10 20:23:12 456";
        String addDate = "0001-02-10 04:00:12 200";
        String resultDate = "1901-03-24 00:23:24 656";
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addDate(addDate);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(15)
    public void checkAddYears() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2035-02-10 20:23:12 456";
        int years = 12;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addYears(years);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(16)
    public void checkAddDays() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2023-03-02 20:23:12 456";
        int days = 20;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addDays(days);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(17)
    public void checkAddMonths() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2024-02-10 20:23:12 456";
        int months = 12;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addMonths(months);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(18)
    public void checkAddHours() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2023-02-12 02:23:12 456";
        int hours = 30;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addHours(hours);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(19)
    public void checkAddMinutes() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2023-02-10 20:53:12 456";
        int minutes = 30;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addMinutes(minutes);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(20)
    public void checkAddSeconds() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2023-02-10 20:28:12 456";
        int seconds = 300;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addSeconds(seconds);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(21)
    public void checkAddMilliseconds() {
        String newDate = "2023-02-10 20:23:12 456";
        String resultDate = "2023-02-10 20:23:42 456";
        int milliseconds = 30000;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.addMilliseconds(milliseconds);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(22)
    public void checkMinusDataToObject() {
        String newDate = "1901-03-24 00:23:24 656";
        String minusDate = "0001-02-10 04:00:12 200";
        String resultDate = "1900-02-10 20:23:12 456";
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        CustomCalendar newCalendar = new CustomCalendar(minusDate);
        testCalendar.minusDate(newCalendar);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(23)
    public void checkMinusDataInStringFormat() {
        String newDate = "1901-03-24 00:23:24 656";
        String minusDate = "0001-02-10 04:00:12 200";
        String resultDate = "1900-02-10 20:23:12 456";
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusDate(minusDate);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(24)
    public void checkMinusYears() {
        String newDate = "2035-02-10 20:23:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int years = 12;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusYears(years);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(26)
    public void checkMinusDays() {
        String newDate = "2023-03-02 20:23:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int days = 20;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusDays(days);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(27)
    public void checkMinusMonths() {
        String newDate = "2024-02-10 20:23:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int months = 12;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusMonths(months);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(28)
    public void checkMinusHours() {
        String newDate = "2023-02-12 02:23:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int hours = 30;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusHours(hours);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(29)
    public void checkMinusMinutes() {
        String newDate = "2023-02-10 20:53:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int minutes = 30;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusMinutes(minutes);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(30)
    public void checkMinusSeconds() {
        String newDate = "2023-02-10 20:28:12 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int seconds = 300;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusSeconds(seconds);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(31)
    public void checkMinusMilliseconds() {
        String newDate = "2023-02-10 20:23:42 456";
        String resultDate = "2023-02-10 20:23:12 456";
        int milliseconds = 30000;
        CustomCalendar testCalendar = new CustomCalendar(newDate);
        testCalendar.minusMilliseconds(milliseconds);
        Assertions.assertEquals(testCalendar.toString(), resultDate);
    }

    @Test
    @Order(31)
    public void checkDifferenceInYears() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2035-02-10 20:23:12 456";
        String years = "Difference in Years 12";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInYears(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), years);
        System.setOut(old);
    }

    @Test
    @Order(32)
    public void checkDifferenceInMonth() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2024-02-10 20:23:12 456";
        String months = "Difference in Months 12";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInMonths(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), months);
        System.setOut(old);
    }

    @Test
    @Order(33)
    public void checkDifferenceInDays() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2023-03-02 20:23:12 456";
        String days = "Difference in Days 20";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInDays(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), days);
        System.setOut(old);
    }

    @Test
    @Order(34)
    public void checkDifferenceInHours() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2023-02-12 02:23:12 456";
        String hours = "Difference in Hours 30";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInHours(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), hours);
        System.setOut(old);
    }

    @Test
    @Order(35)
    public void checkDifferenceInMinutes() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2023-02-10 20:53:12 456";
        String minutes = "Difference in Minutes 30";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInMinutes(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), minutes);
        System.setOut(old);
    }

    @Test
    @Order(36)
    public void checkDifferenceInSeconds() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2023-02-10 20:28:12 456";
        String seconds = "Difference in Seconds 300";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInSeconds(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), seconds);
        System.setOut(old);
    }

    @Test
    @Order(37)
    public void checkDifferenceInMilliseconds() {
        PrintStream old = System.out;
        System.setOut(new PrintStream(output));
        String firstDate = "2023-02-10 20:23:12 456";
        String secondDate = "2023-02-10 20:23:42 456";
        String milliseconds = "Difference in Milliseconds 30000";
        CustomCalendar testCalendar = new CustomCalendar(firstDate);
        CustomCalendar newCalendar = new CustomCalendar(secondDate);
        testCalendar.differenceInMilliseconds(testCalendar, newCalendar);
        Assertions.assertEquals(output.toString().replaceAll("\r\n", ""), milliseconds);
        System.setOut(old);
    }

}