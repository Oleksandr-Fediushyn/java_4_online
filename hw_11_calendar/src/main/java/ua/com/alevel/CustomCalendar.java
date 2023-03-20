package ua.com.alevel;

import java.util.*;

public class CustomCalendar {

    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;


    public CustomCalendar() {
        initDate();
    }

    public CustomCalendar(String format) {
        set(format);
    }

    public CustomCalendar(long time) {
        long calcSeconds = time / ONE_SECOND;
        long calcMinutes = time / ONE_MINUTE;
        long calcHours = time / ONE_HOUR;
        int calcDays = (int) (time / ONE_DAY);
        int leftoverHours = (int) (calcHours % 24);
        int leftoeverMinutes = (int) (calcMinutes % 60);
        int leftoverSeconds = (int) (calcSeconds % 60);
        int leftoverMilliseconds = (int) (time % 1000);
        int years = 0;
        int calcMonth = 0;
        int dayInMonth = 0;
        calcDays++;
        boolean calc = true;
        int k = 0;
        while (calc) {
            boolean calcYear = true;
            for (int m = 1; m < 13; m++) {
                k++;
                if (calcDays > CalendarUtilities.totalDaysOfMonth(m, years)) {
                    dayInMonth = calcDays - CalendarUtilities.totalDaysOfMonth(m, years);
                    calcDays = dayInMonth;
                } else {
                    dayInMonth = Math.abs(calcDays);
                    calcYear = false;
                    calc = false;
                    break;
                }
            }
            if (calcYear) {
                years++;
            }
        }
        if (k % 12 == 0) {
            calcMonth = 12;
        } else {
            calcMonth = k % 12;
        }
        this.set(years, calcMonth, dayInMonth, leftoverHours, leftoeverMinutes, leftoverSeconds, leftoverMilliseconds);
    }

    public void printDataTimeFullFormat() {
        System.out.format("%04d" + "-" + "%02d" + "-" + "%02d" + " " + "%02d" +
                ":" + "%02d" + ":" + "%02d" + " " + "%03d %n", year, month, day, hours, minutes, seconds, milliseconds);
    }

    public void now() {
        initDate();
        System.out.println("Current DATE and TIME:");
        printDataTimeFullFormat();
    }

    public void initDate() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hours = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.seconds = calendar.get(Calendar.SECOND);
        this.milliseconds = calendar.get(Calendar.MILLISECOND);
    }

    public long getTimeMillis() {
        int totalDays = year * 365 + CalendarUtilities.countLeapYears(year);
        int totalMonthDays = CalendarUtilities.countDaysToMonth(month, year);
        int days = totalDays + totalMonthDays + day - 1;
        return (long) days * ONE_DAY + (long) hours * ONE_HOUR + (long) minutes * ONE_MINUTE + (long) seconds * ONE_SECOND + milliseconds;
    }

    public void set(int year, int month) {
        if (year >= 0 && month >= 1 && month <= 12) {
            this.year = year;
            this.month = month;
        } else System.out.println("You enter not correct date format");
    }

    public void set(int year, int month, int day, int hour, int minutes) {
        if (year > 0 && month >= 1 && month <= 12 && day >= 1 && day <= 31
                && hour >= 0 && hour < 24 && minutes >= 0 && minutes <= 59) {
            set(year, month);
            this.day = day;
            this.hours = hour;
            this.minutes = minutes;
        } else System.out.println("You enter not correct date format");
    }

    public void set(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds) {
        this.set(year, month, day, hour, minutes);
        if (seconds >= 0 && seconds <= 59 && milliseconds >= 0 && milliseconds < 1000) {
            this.seconds = seconds;
            this.milliseconds = milliseconds;
        } else System.out.println("You enter not correct date format");
    }

    public void set(String format) {
        format = format.trim();
        boolean[] checkDateFormat = {
                format.matches("\\d{4}[- -.](0[1-9]|1[012])[- -.](0[1-9]|[12][0-9]|3[01])"),
                format.matches("\\d{4}[- -.](0[1-9]|1[012])[- -.](0[1-9]|[12][0-9]|3[01])" +
                        "\\s(0[1-9]|1[0-9]|2[0-3])[- :.](0[1-9]|[0-5][0-9])"),
                format.matches("\\d{4}[- -.](0[1-9]|1[012])[- -.](0[1-9]|[12][0-9]|3[01])" +
                        "\\s(0[1-9]|[01][0-9]|2[0-3])[- :.](0[1-9]|[0-5][0-9])[- :.](0[1-9]|[0-5][0-9])"),
                format.matches("\\d{4}[- -.](0[1-9]|1[012])[- -.](0[1-9]|[12][0-9]|3[01])" +
                        "\\s(0[1-9]|[01][0-9]|2[0-3])[- :.](0[1-9]|[0-5][0-9])[- :.](0[1-9]|[0-5][0-9])\\s\\d{3}")
        };
        int key = 0;
        for (int i = 0; i < checkDateFormat.length; i++) {
            if (checkDateFormat[i]) {
                key = i;
                key++;
            }
        }
        int[] date;

        switch (key) {

            case 0:
                System.out.println("You enter not correct date format");
                break;
            case 1: {
                date = CalendarUtilities.parseDate(format);
                set(date[0], date[1], date[2], 0, 0, 0, 0);
                break;
            }
            case 2: {
                date = CalendarUtilities.parseDateTime(format);
                set(date[0], date[1], date[2], date[3], date[4], 0, 0);
                break;
            }
            case 3: {
                date = CalendarUtilities.parseDateTime(format);
                set(date[0], date[1], date[2], date[3], date[4], date[5], 0);
                break;
            }
            case 4: {
                date = CalendarUtilities.parseDateTime(format);
                set(date[0], date[1], date[2], date[3], date[4], date[5], CalendarUtilities.millSec(format));
                break;
            }
        }
    }

    public void addDate(CustomCalendar date) {
        long currentTime = this.getTimeMillis() + date.getTimeMillis();
        CustomCalendar newCalendar = new CustomCalendar(currentTime);
        this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
    }

    public void addDate(String format) {
        CustomCalendar newDate = new CustomCalendar(format);
        long currentTime = this.getTimeMillis() + newDate.getTimeMillis();
        CustomCalendar newCalendar = new CustomCalendar(currentTime);
        this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
    }

    public void addYears(int years) {
        if (years > 0) {
            this.year = this.year + years;
        } else System.out.println("You Enter not correct number of years");
    }

    public void addMonths(int months) {
        if (months > 0) {
            int newMonth = this.month + months;
            if (newMonth > 12) {
                if (newMonth % 12 == 0) {
                    this.month = 12;
                    this.addYears(newMonth / 12);
                } else {
                    this.month = newMonth % 12;
                    this.addYears((int) Math.floor(newMonth / 12));
                }
            } else this.month = newMonth;
        } else System.out.println("You Enter not correct number of months");
    }

    public void addDays(int days) {
        if (days > 0) {
            long currentTime = this.getTimeMillis() + (long) days * ONE_DAY;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of days");
    }

    public void addHours(int hours) {
        if (hours > 0) {
            long currentTime = this.getTimeMillis() + (long) hours * ONE_HOUR;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of hours");
    }

    public void addMinutes(int minutes) {
        if (minutes > 0) {
            long currentTime = this.getTimeMillis() + (long) minutes * ONE_MINUTE;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of minutes");
    }

    public void addSeconds(int seconds) {
        if (seconds > 0) {
            long currentTime = this.getTimeMillis() + (long) seconds * ONE_SECOND;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of seconds");
    }

    public void addMilliseconds(int milliseconds) {
        if (milliseconds > 0) {
            long currentTime = this.getTimeMillis() + (long) milliseconds;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of milliseconds");
    }

    public void minusDate(CustomCalendar date) {
        long currentTime = this.getTimeMillis() - date.getTimeMillis();
        if (currentTime > 0) {
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter Date greater then current");
    }

    public void minusDate(String format) {
        CustomCalendar newDate = new CustomCalendar(format);
        long currentTime = this.getTimeMillis() - newDate.getTimeMillis();
        if (currentTime > 0) {
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter Date greater then current");
    }

    public void minusYears(int years) {
        if (years < this.year) {
            this.year = this.year - years;
        } else System.out.println("You Enter not correct number of years");
    }

    public void minusMonths(int months) {
        if (months <= 0) {
            System.out.println("You Enter not correct number of months");
        } else {
            if (this.month > months) {
                this.month = this.month - months;
            } else {
                if (months > 12) {
                    if (months % 12 == 0) {
                        this.minusYears(months / 12);
                    } else {
                        int newMonth = months % 12;
                        if (this.month > newMonth) {
                            this.month = this.month - newMonth;
                            this.minusYears(((int) Math.floor(months / 12)));
                        } else {
                            this.month = 12 - (newMonth - this.month);
                            this.minusYears(((int) Math.floor(months / 12)) + 1);
                        }
                    }
                } else {
                    this.month = 12 - (months - this.month);
                    this.minusYears(1);
                }
            }
        }
    }

    public void minusDays(int days) {
        if (days > 0) {
            long currentTime = this.getTimeMillis() - (long) days * ONE_DAY;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of days");
    }

    public void minusHours(int hours) {
        if (hours > 0) {
            long currentTime = this.getTimeMillis() - (long) hours * ONE_HOUR;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of hours");
    }

    public void minusMinutes(int minutes) {
        if (minutes > 0) {
            long currentTime = this.getTimeMillis() - (long) minutes * ONE_MINUTE;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of minutes");
    }

    public void minusSeconds(int seconds) {
        if (seconds > 0) {
            long currentTime = this.getTimeMillis() - (long) seconds * ONE_SECOND;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of seconds");
    }

    public void minusMilliseconds(int milliseconds) {
        if (milliseconds > 0) {
            long currentTime = this.getTimeMillis() - (long) milliseconds;
            CustomCalendar newCalendar = new CustomCalendar(currentTime);
            this.set(newCalendar.year, newCalendar.month, newCalendar.day, newCalendar.hours,
                    newCalendar.minutes, newCalendar.seconds, newCalendar.milliseconds);
        } else System.out.println("You Enter not correct number of milliseconds");
    }

    public void differenceInYears(CustomCalendar first, CustomCalendar second) {
        if (first.year > second.year) {
            System.out.println("You Enter not correct Date");
        } else {
            int differenceInYears = 0;
            if ((second.month < first.month) || ((second.month == first.month) && second.day < first.day)) {
                differenceInYears = second.year - first.year - 1;
            } else {
                differenceInYears = second.year - first.year;
            }
            System.out.println("Difference in Years " + differenceInYears);
        }
    }

    public void differenceInMonths(CustomCalendar first, CustomCalendar second) {
        if (first.year > second.year) {
            System.out.println("You Enter not correct Date");
        } else {
            int differenceInMonths = 0;
            differenceInMonths = (second.year - first.year) * 12;
            if (second.day < first.day) {
                differenceInMonths = differenceInMonths + (second.month - first.month) - 1;
            } else {
                differenceInMonths = differenceInMonths + (second.month - first.month);
            }
            System.out.println("Difference in Months " + differenceInMonths);
        }
    }

    public void differenceInDays(CustomCalendar first, CustomCalendar second) {
        long differenceInMilliseconds = second.getTimeMillis() - first.getTimeMillis();
        if (differenceInMilliseconds < 0) {
            System.out.println("You Enter not correct Date");
        } else {
            long differenceInDays = (long) Math.floor(differenceInMilliseconds / ONE_DAY);
            System.out.println("Difference in Days " + differenceInDays);
        }
    }

    public void differenceInHours(CustomCalendar first, CustomCalendar second) {
        long differenceInMilliseconds = second.getTimeMillis() - first.getTimeMillis();
        if (differenceInMilliseconds < 0) {
            System.out.println("You Enter not correct Date");
        } else {
            long differenceInHours = differenceInMilliseconds / ONE_HOUR;
            System.out.println("Difference in Hours " + differenceInHours);
        }
    }

    public void differenceInMinutes(CustomCalendar first, CustomCalendar second) {
        long differenceInMilliseconds = second.getTimeMillis() - first.getTimeMillis();
        if (differenceInMilliseconds < 0) {
            System.out.println("You Enter not correct Date");
        } else {
            long differenceInMinutes = differenceInMilliseconds / ONE_MINUTE;
            System.out.println("Difference in Minutes " + differenceInMinutes);
        }
    }

    public void differenceInSeconds(CustomCalendar first, CustomCalendar second) {
        long differenceInMilliseconds = second.getTimeMillis() - first.getTimeMillis();
        if (differenceInMilliseconds < 0) {
            System.out.println("You Enter not correct Date");
        } else {
            long differenceInSeconds = differenceInMilliseconds / ONE_SECOND;
            System.out.println("Difference in Seconds " + differenceInSeconds);
        }
    }

    public void differenceInMilliseconds(CustomCalendar first, CustomCalendar second) {
        long differenceInMilliseconds = second.getTimeMillis() - first.getTimeMillis();
        if (differenceInMilliseconds < 0) {
            System.out.println("You Enter not correct Date");
        } else {
            System.out.println("Difference in Milliseconds " + differenceInMilliseconds);
        }
    }

    @Override
    public String toString() {
        return String.format("%04d" + "-" + "%02d" + "-" + "%02d" + " " + "%02d" +
                ":" + "%02d" + ":" + "%02d" + " " + "%03d", year, month, day, hours, minutes, seconds, milliseconds);
    }

}
