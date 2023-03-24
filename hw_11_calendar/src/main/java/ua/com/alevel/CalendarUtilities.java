package ua.com.alevel;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CalendarUtilities {

    public static final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeapYear(int y) {
        if ((y % 4 == 0) && (((y % 100 == 0 && y % 400 == 0) || (y % 100 != 0)))) {
            return true;
        } else {
            return false;
        }
    }

    public static int countLeapYears(int year2) {
        int count = 0;
        for (int i = 0; i < year2; i++) {
            if (isLeapYear(i)) {
                count += 1;
            }
        }
        return count;
    }

    public static int totalDaysOfMonth(int m, int y) {
        if (m == 2 && isLeapYear(y)) {
            return 29;
        } else {
            return daysOfMonth[m - 1];
        }
    }

    public static int countDaysToMonth(int month, int year) {
        int count = 0;
        for (int m = 1; m < month; m++) {
            count += totalDaysOfMonth(m, year);
        }
        return count;
    }

    public static int[] parseDate(String format) {

        return Stream.of(format.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int[] parseDateTime(String format) {
        String[] parse = format.split("\\s");
        int[] numberData = parseDate(parse[0]);
        int[] numberTime = Stream.of(parse[1].split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return IntStream.concat(Arrays.stream(numberData), Arrays.stream(numberTime)).toArray();
    }

    public static int millSec(String format) {
        String[] parse = format.split("\\s");
        return Integer.parseInt(parse[2]);
    }
}
