package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomCalendarMenu {

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            customCalendarOperations(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want see current time, please enter 1");
        System.out.println("If you want see current time in milliseconds, please enter 2");
        System.out.println("If you want see time in milliseconds, please enter 3");
        System.out.println("If you want set time in milliseconds, please enter 4");
        System.out.println("If you want set time in string format, please enter 5");
        System.out.println("If you want add Date to current, please enter 6");
        System.out.println("If you want add Date to current in text format, please enter 7");
        System.out.println("If you want minus Date from current, please enter 8");
        System.out.println("If you want minus Date from current in text format, please enter 9");
        System.out.println("If you want find difference between Dates, please enter 10");
        System.out.println("If you want add time to date, please enter 11");
        System.out.println("If you want minus time from date, please enter 12");
        System.out.println("If you want close application, please enter 13");
        System.out.println();
    }

    private void menuAddTime() {
        System.out.println();
        System.out.println("If you want add time in years, please enter 1");
        System.out.println("If you want add time in months, please enter 2");
        System.out.println("If you want add time in days, please enter 3");
        System.out.println("If you want add time in hours, please enter 4");
        System.out.println("If you want add time in minutes, please enter 5");
        System.out.println("If you want add time in seconds, please enter 6");
        System.out.println("If you want add time in milliseconds, please enter 7");
        System.out.println("If you want exit, please enter 8");
        System.out.println();
    }

    private void menuMinusTime() {
        System.out.println();
        System.out.println("If you want minus time in years, please enter 1");
        System.out.println("If you want minus time in months, please enter 2");
        System.out.println("If you want minus time in days, please enter 3");
        System.out.println("If you want minus time in hours, please enter 4");
        System.out.println("If you want minus time in minutes, please enter 5");
        System.out.println("If you want minus time in seconds, please enter 6");
        System.out.println("If you want minus time in milliseconds, please enter 7");
        System.out.println("If you want exit, please enter 8");
        System.out.println();
    }

    private void addTimeOperations(BufferedReader reader, String select, CustomCalendar date) throws IOException {
        switch (select) {
            case "1" : {System.out.println("Please enter number of years, which you want add");
                int years = Integer.parseInt(reader.readLine());date.addYears(years); break;}
            case "2" : {System.out.println("Please enter number of months, which you want add");
                int months = Integer.parseInt(reader.readLine());date.addMonths(months); break;}
            case "3" : {System.out.println("Please enter number of days, which you want add");
                int days = Integer.parseInt(reader.readLine());date.addDays(days); break;}
            case "4" : {System.out.println("Please enter number of hours, which you want add");
                int hours = Integer.parseInt(reader.readLine());date.addHours(hours); break;}
            case "5" : {System.out.println("Please enter number of minutes, which you want add");
                int minutes = Integer.parseInt(reader.readLine());date.addMinutes(minutes); break;}
            case "6" : {System.out.println("Please enter number of seconds, which you want add");
                int seconds = Integer.parseInt(reader.readLine());date.addSeconds(seconds); break;}
            case "7" : {System.out.println("Please enter number of milliseconds, which you want add");
                int milliseconds = Integer.parseInt(reader.readLine());date.addMilliseconds(milliseconds); break;}
            case "8" : break;
        }
    }

    private void minusTimeOperations(BufferedReader reader, String select, CustomCalendar date) throws IOException {
        switch (select) {
            case "1" : {System.out.println("Please enter number of years, which you want add");
                int years = Integer.parseInt(reader.readLine());date.minusYears(years); break;}
            case "2" : {System.out.println("Please enter number of months, which you want add");
                int months = Integer.parseInt(reader.readLine());date.minusMonths(months); break;}
            case "3" : {System.out.println("Please enter number of days, which you want add");
                int days = Integer.parseInt(reader.readLine());date.minusDays(days); break;}
            case "4" : {System.out.println("Please enter number of hours, which you want add");
                int hours = Integer.parseInt(reader.readLine());date.minusHours(hours); break;}
            case "5" : {System.out.println("Please enter number of minutes, which you want add");
                int minutes = Integer.parseInt(reader.readLine());date.minusMinutes(minutes); break;}
            case "6" : {System.out.println("Please enter number of seconds, which you want add");
                int seconds = Integer.parseInt(reader.readLine());date.minusSeconds(seconds); break;}
            case "7" : {System.out.println("Please enter number of milliseconds, which you want add");
                int milliseconds = Integer.parseInt(reader.readLine());date.minusMilliseconds(milliseconds); break;}
            case "8" : break;
        }
    }

    private void customCalendarOperations(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : printCurrentDateTime(); break;
            case "2" : printCurrentTimeInMilliseconds(); break;
            case "3" : printTimeInMilliseconds(reader); break;
            case "4" : setTimeInMilliseconds(reader); break;
            case "5" : setTimeInStringFormat(reader); break;
            case "6" : addDateFormat(reader); break;
            case "7" : addDateInTextFormat(reader); break;
            case "8" : minusDate(reader); break;
            case "9" : minusDateInTextFormat(reader); break;
            case "10" : differenceBetweenDates(reader); break;
            case "11" : addTime(reader); break;
            case "12" : minusTime(reader); break;
            case "13" : stop(); break;
        }
        menu();
    }

    private void printCurrentDateTime() {
        CustomCalendar customCalendar = new CustomCalendar();
        customCalendar.now();

    }

    private void printCurrentTimeInMilliseconds() {
        CustomCalendar customCalendar = new CustomCalendar();
        customCalendar.now();
        System.out.println("Time in milliseconds:  " + customCalendar.getTimeMillis());
    }

    private void printTimeInMilliseconds(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customCalendar = new CustomCalendar(newTime);
        System.out.println(customCalendar);
        System.out.println("Time in milliseconds:  " + customCalendar.getTimeMillis());
    }

    private void setTimeInMilliseconds(BufferedReader reader) throws IOException {
        System.out.println("Please enter the number of milliseconds");
        long newTime = Long.parseLong(reader.readLine());
        CustomCalendar customCalendar = new CustomCalendar(newTime);
        System.out.println("This time in milliseconds in DateTime format:");
        System.out.println(customCalendar);
    }

    private void setTimeInStringFormat(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customCalendar = new CustomCalendar(newTime);
        System.out.println(customCalendar);
    }

    private void addDateFormat(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customCalendar = new CustomCalendar(newTime);
        CustomCalendar newCustomCalendar = new CustomCalendar();
        newCustomCalendar.addDate(customCalendar);
        System.out.println("New value in DateTime format:");
        System.out.println(newCustomCalendar);
    }

    private void addDateInTextFormat(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar newCustomCalendar = new CustomCalendar();
        newCustomCalendar.addDate(newTime);
        System.out.println("New value in DateTime format:");
        System.out.println(newCustomCalendar);
    }

    private void minusDate(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customCalendar = new CustomCalendar(newTime);
        CustomCalendar newCustomCalendar = new CustomCalendar();
        newCustomCalendar.minusDate(customCalendar);
        System.out.println("New value in DateTime format:");
        System.out.println(newCustomCalendar);
    }

    private void minusDateInTextFormat(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar newCustomCalendar = new CustomCalendar();
        newCustomCalendar.minusDate(newTime);
        System.out.println("New value in DateTime format:");
        System.out.println(newCustomCalendar);
    }

    private void differenceBetweenDates(BufferedReader reader) throws IOException {
        System.out.println("Please enter the start DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar custom1 = new CustomCalendar(newTime);
        System.out.println("Please enter the final DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime2 = reader.readLine();
        CustomCalendar custom2 = new CustomCalendar(newTime2);
        CustomCalendar newCustomCalendar = new CustomCalendar();
        newCustomCalendar.differenceInYears(custom1, custom2);
        newCustomCalendar.differenceInMonths(custom1, custom2);
        newCustomCalendar.differenceInDays(custom1, custom2);
        newCustomCalendar.differenceInHours(custom1, custom2);
        newCustomCalendar.differenceInMinutes(custom1, custom2);
        newCustomCalendar.differenceInSeconds(custom1, custom2);
        newCustomCalendar.differenceInMilliseconds(custom1, custom2);
    }

    private void addTime(BufferedReader reader) throws IOException {
        System.out.println("Please enter the DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customDate = new CustomCalendar(newTime);
        System.out.println("You enter DATE:");
        System.out.println(customDate);
        System.out.println("Select your options");
        String select;
        menuAddTime();
        if((select = reader.readLine()) != null) {
            addTimeOperations(reader, select,customDate);
            System.out.println("NEW value of DATE: ");
            System.out.println(customDate);
        }
        else System.out.println("You enter not correct options");
    }

    private void minusTime(BufferedReader reader) throws IOException {
        System.out.println("Please enter the start DATE in one from the next format: ");
        System.out.println("YYYY-MM-DD or YYYY-MM-DD HH:MM or YYYY-MM-DD HH:MM:SS or YYYY-MM-DD HH:MM:SS Ms");
        String newTime = reader.readLine();
        CustomCalendar customDate = new CustomCalendar(newTime);
        System.out.println("You enter DATE:");
        System.out.println(customDate);
        System.out.println("Select your options");
        String select;
        menuMinusTime();
        if((select = reader.readLine()) != null) {
            minusTimeOperations(reader, select,customDate);
            System.out.println("NEW value of DATE: ");
            System.out.println(customDate);
        }
        else System.out.println("You enter not correct options");
    }

    private void stop() {
        System.exit(0);
    }
}
