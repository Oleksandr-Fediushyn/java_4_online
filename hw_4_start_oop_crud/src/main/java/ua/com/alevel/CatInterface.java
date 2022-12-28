package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CatInterface {
    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your options");
        String select;
        menu();
        while ((select = reader.readLine()) != null) {
            crud(reader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create pet, please enter 1");
        System.out.println("If you want find pet, please enter 2");
        System.out.println("If you want delete pet, please enter 3");
        System.out.println("If you want print all pets, please enter 4");
        System.out.println("If you want close application, please enter 5");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findByName(reader); break;
            case "3" : deleteByName(reader); break;
            case "4" : findAll(); break;
            case "5" : exit(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create pet");
        System.out.println("Please enter name");
        String name = reader.readLine();
        System.out.println("Please enter the sex of the pet");
        String sex = reader.readLine();
        System.out.println("Please enter age of the pet");
        Integer age = Integer.parseInt(reader.readLine());
        System.out.println("Please enter weight of the pet");
        Double weight = Double.parseDouble(reader.readLine());
        System.out.println("Please enter color of the pet");
        String color = reader.readLine();
        System.out.println("Please enter temperament of the pet");
        String temperament = reader.readLine();
        System.out.println("If your pet is vaccinated, then enter - yes, otherwise - no");
        String isVaccinated = reader.readLine();

        Cat cat = new Cat();
        cat.setName(name);
        cat.setSex(sex);
        cat.setAge(age);
        cat.setWeight(weight);
        cat.setColor(color);
        cat.setTemperament(temperament);
        cat.setIsVaccinated(isVaccinated);

        CatStorage.addCat(cat);
    }

    private void findByName(BufferedReader reader) throws IOException {
        System.out.println("Find pet by name");
        String name = reader.readLine();
        Cat cat = CatStorage.getCat(name);
        System.out.println("cat = " + cat);
    }

    private void deleteByName(BufferedReader reader) throws IOException {
        System.out.println("Delete pets by name");
        String name = reader.readLine();
        CatStorage.deleteCat(name);
    }

    private void findAll() {
        System.out.println("Print all pets");
       Cat [] cats = CatStorage.getCats();
       System.out.println("cats = " + Arrays.toString(cats));
    }

    private void exit() {
        System.exit(0);
    }

}





