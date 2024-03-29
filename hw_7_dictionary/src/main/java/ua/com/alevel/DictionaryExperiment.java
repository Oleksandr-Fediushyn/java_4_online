package ua.com.alevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictionaryExperiment {

    void testDictionaryInteger() {
        Dictionary <Integer, Integer> theHashTable = new Dictionary<>();
        System.out.println("Add 5 Entry to Dictionary with Integer value");
        theHashTable.put(0,100);
        theHashTable.put(1,300);
        theHashTable.put(2,200);
        theHashTable.put(3,500);
        theHashTable.put(1000,10);
        System.out.println();
        System.out.println("See all  Dictionary key after add Entry :");
        System.out.println();
        Set<Integer> keySet = new HashSet<>();
        keySet = theHashTable.keySet();
        System.out.println(keySet);
        System.out.println();
        System.out.println("Check size after add Entry :");
        System.out.println(theHashTable.size());
        System.out.println();
        System.out.println("See Value for Entry with key 0:");
        System.out.println(theHashTable.get(0));
        System.out.println();
        System.out.println("See Value for Entry with key 10:");
        System.out.println(theHashTable.get(10));
        System.out.println();
        System.out.println("Check contain  key 145 in Dictionary :");
        System.out.println(theHashTable.containsKey(145));
        System.out.println();
        System.out.println("Check contain  key 3 in Dictionary :");
        System.out.println(theHashTable.containsKey(3));
        System.out.println();
        System.out.println("Remove Entry with key 3 from Dictionary");
        System.out.println(theHashTable.remove(3));
        System.out.println();
        System.out.println("Check size after Remove Entry with key 3 from Dictionary");
        System.out.println(theHashTable.size());
        System.out.println();
        System.out.println("Add  Entry with key 3 and new Value");
        theHashTable.put(3,500);
        System.out.println();
        System.out.println("Check size after Add Entry with key 3");
        System.out.println(theHashTable.size());
        System.out.println();
        System.out.println("Check contain  Value 500 in Dictionary :");
        System.out.println(theHashTable.containsValue(500));
        System.out.println();
        System.out.println("Create new Dictionary and Add Entries from old Dictionary:");
        Dictionary <Integer, Integer> theHashTable2 = new Dictionary <> ();
        theHashTable2.putAll(theHashTable);
        System.out.println("Check size after Add Entries from Dictionary");
        System.out.println(theHashTable2.size());
        System.out.println();
        System.out.println("Print all keys from Dictionary :");
        keySet = theHashTable2.keySet();
        System.out.println(keySet);
        System.out.println();
        System.out.println("Print all values from Dictionary :");
        List<Integer> collectValue = new ArrayList<>();
        collectValue = (List<Integer>) theHashTable.values();
        System.out.println(collectValue);
        System.out.println();
        theHashTable2.clear();
        System.out.println("size after clear Dictionary");
        System.out.println(theHashTable2.size());
    }
    void testDictionaryString() {
        Dictionary <String, String> theHashTableString = new Dictionary<>();
        System.out.println("Add 5 Entry to Dictionary with Integer value");
        theHashTableString.put("0","100");
        theHashTableString.put("1","300");
        theHashTableString.put("2","200");
        theHashTableString.put("3","500");
        theHashTableString.put("1000","10");
        System.out.println();
        System.out.println("See all  Dictionary key after add Entry :");
        System.out.println();
        Set<String> keySet = new HashSet<>();
        keySet = theHashTableString.keySet();
        System.out.println(keySet);
        System.out.println();
        System.out.println("Check size after add Entry :");
        System.out.println(theHashTableString.size());
        System.out.println();
        System.out.println("See Value for Entry with key 0:");
        System.out.println(theHashTableString.get("0"));
        System.out.println();
        System.out.println("See Value for Entry with key 10:");
        System.out.println(theHashTableString.get("10"));
        System.out.println();
        System.out.println("Check contain  key 145 in Dictionary :");
        System.out.println(theHashTableString.containsKey("145"));
        System.out.println();
        System.out.println("Check contain  key 3 in Dictionary :");
        System.out.println(theHashTableString.containsKey("3"));
        System.out.println();
        System.out.println("Remove Entry with key 3 from Dictionary");
        System.out.println(theHashTableString.remove("3"));
        System.out.println();
        System.out.println("Check size after Remove Entry with key 3 from Dictionary");
        System.out.println(theHashTableString.size());
        System.out.println();
        System.out.println("Add  Entry with key 3 and new Value");
        theHashTableString.put("3","500");
        System.out.println();
        System.out.println("Check size after Add Entry with key 3");
        System.out.println(theHashTableString.size());
        System.out.println();
        System.out.println("Check contain  Value 500 in Dictionary :");
        System.out.println(theHashTableString.containsValue("500"));
        System.out.println();
        System.out.println("Create new Dictionary and Add Entries from old Dictionary:");
        Dictionary <String, String> theHashTableString2 = new Dictionary <> ();
        theHashTableString2.putAll(theHashTableString);
        System.out.println("Check size after Add Entries from Dictionary");
        System.out.println(theHashTableString2.size());
        System.out.println();
        System.out.println("Print all keys from Dictionary :");
        keySet = theHashTableString2.keySet();
        System.out.println(keySet);
        System.out.println();
        System.out.println("Print all values from Dictionary :");
        List<String> collectValue = new ArrayList<>();
        collectValue = (List<String>) theHashTableString2.values();
        System.out.println(collectValue);
    }
}
