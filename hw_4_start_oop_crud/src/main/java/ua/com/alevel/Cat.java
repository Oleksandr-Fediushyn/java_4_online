package ua.com.alevel;

import java.lang.*;

public class Cat {
    private String name;
    private String sex;
    private Integer age;
    private Double weight;
    private String color;
    private String temperament;
    private String isVaccinated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.name = name;
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (name.matches(".*\\d.*")) {
            System.out.println("The field 'sex' must contain only characters");
        } else {
            this.sex = sex;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 0) {
            this.age = 0;
            System.out.println("Weight value must be greater than zero");
        } else {
            this.age = age;
        }

    }


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if (weight < 0) {
            System.out.println("Weight value must be greater than zero");
        } else {
            this.weight = weight;
        }

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {

        if (color.matches(".*\\d.*")) {
            System.out.println("The field 'color' must contain only characters");
        } else {
            this.color = color;
        }

    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        if (temperament.matches(".*\\d.*")) {
            System.out.println("The field 'temperament' must contain only characters");
        } else {
            this.temperament = temperament;
        }

    }

    public String getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(String isVaccinated) {
        if (isVaccinated.matches(".*\\d.*")) {
            System.out.println("The field 'isVaccinated' must contain only characters");
        } else {
            this.isVaccinated = isVaccinated;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", color='" + color + '\'' +
                ", temperament='" + temperament + '\'' +
                ", isVaccinated='" + isVaccinated + '\'' +
                '}';
    }

}
