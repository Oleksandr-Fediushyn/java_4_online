package ua.com.alevel;

import java.util.Arrays;

public class CatStorage {

    private static Integer n = 2 ;
    private static Integer count = 0;
    private static Cat[] catsArray = new Cat[n];

    private CatStorage() {

    }

    public static Cat[] getCats() {

       return catsArray;
    }

    public static void addCat(Cat cat) {

        if (n > count) {
            catsArray[count] = cat;
        } else {
            Cat newcatsArray[] = new Cat[n + 1];

            for (int i = 0; i < n; i++)
                newcatsArray[i] = catsArray[i];

            newcatsArray[n] = cat;
            catsArray = newcatsArray;
            n++;
        }
        count++;
    }

    public static void checkNullInArray()
    {
        Cat [] freshArray = new Cat [catsArray.length-1];

        for(int i=0, k=0; i < catsArray.length; i++){
            if(catsArray[i] != null){
                freshArray[k] = catsArray[i];
                k++;
            }
            n = k;
        }
        catsArray = freshArray;
    }

    public static void deleteCat(String name) {

        if (n != count)
            checkNullInArray();

        Integer searchIndex = 0;
        for (int i = 0; i < catsArray.length; i++) {

            if (catsArray[i].getName().equals(name))
                searchIndex = i;
        }

       Cat[] newcatsArray = new  Cat[catsArray.length-1];
        Integer j = searchIndex;
        for(int i=0, k=0; i < catsArray.length; i++){
            if(i!=j){
                newcatsArray[k] = catsArray[i];
                k++;
            }
        }
        catsArray = newcatsArray;
        n--;
        count--;
        System.out.println("Entry deleted successfully !");
    }

    public static Cat getCat(String name) {

        if (n != count)
            checkNullInArray();

        for (int i = 0; i < catsArray.length; i++) {

            if (catsArray[i].getName().equals(name)) return catsArray[i];
        }
        return null;
    }
}
