package ua.com.alevel;


public class Main {

    public static void main(String[] args) {

        DictionaryExperiment test = new DictionaryExperiment();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Work with Dictionary <K, V> where using Integer");
        System.out.println("-------------------------------------------------------------");
        test.testDictionaryInteger();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Work with Dictionary <K, V> where using String");
        System.out.println("-------------------------------------------------------------");
        test.testDictionaryString();
    }
}