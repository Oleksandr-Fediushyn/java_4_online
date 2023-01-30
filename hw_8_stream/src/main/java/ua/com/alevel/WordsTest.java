package ua.com.alevel;

import java.util.*;
import java.util.stream.Collectors;

public class WordsTest {
    List<String> sentencies = new ArrayList<>();
    Map<String, Integer> uniqueWords = new LinkedHashMap<>();
    List<Integer> printWordsCountList = new ArrayList<>();
    List<Double> percentage = new ArrayList<>();
    Integer sumWords = 0;

     public WordsTest ( String userSentencies)
     {
         userSentencies = userSentencies.trim();
         sentencies = List.of(userSentencies.replaceAll("\\W", " "));
     }

     List<String> getSentencies(){
         return sentencies.stream().map(w -> w.replaceAll("\\s+", " "))
                 .map(w -> w.split(" "))
                 .flatMap(Arrays::stream)
                 .map(String::toLowerCase)
                 .collect(Collectors.toList());
     }

    Map<String, Integer> getUniqueWords(){
        return getSentencies().stream().collect(Collectors.toMap(key -> key, val -> 1, Integer::sum)).entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    List<String> getListUniqueWords(){
         return this.getUniqueWords().keySet().
                 stream().collect(Collectors.toList());
    }

    List<Integer> getPrintWordsCountList(){
         return this.getUniqueWords().values().stream().toList();
    }

    List<Double> getPercentage(){
        try{
         sumWords  = this.getUniqueWords().values().
                stream().toList().
                stream().reduce(Integer::sum).get();
        if (sumWords == 0) throw new ArithmeticException();
        percentage = this.getUniqueWords().values().stream().toList().
                stream().mapToDouble(x -> x).
                map(a -> Math.round(a*100/sumWords)).boxed().toList();
        }
        catch (ArithmeticException e) {
            System.out.println ("Can't be divided by Zero " + e);
        }
        return percentage;
    }

}
