package ua.com.alevel;

import java.util.stream.Stream;

public class PrintTableTest {

    WordsTest printWordTest;

    public PrintTableTest (String userString){
        printWordTest = new WordsTest(userString);
    }

    String leftAlignFormat = "| %-15s | %-6d | %-6d | %-11d |%n";

    public void PrintTableInConsole(){

        System.out.format("+-----------------+--------+--------+-------------+%n");
        System.out.format("|       Words     | Rating |  Count |  Percentage |%n");
        System.out.format("+-----------------+--------+--------+-------------+%n");
        Stream.iterate(0, (i -> i < printWordTest.getUniqueWords().size()), (i -> ++i))
                .forEach(a -> System.out.format(leftAlignFormat, printWordTest.getListUniqueWords().get(a), a+1,
                        printWordTest.getPrintWordsCountList().get(a),printWordTest.getPercentage().get(a).intValue()));
        System.out.format("+-----------------+--------+--------+-------------+%n");
    }

}




