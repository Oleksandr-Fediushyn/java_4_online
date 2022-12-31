package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private String sex;
    private Integer age;
    private String jobTitle;
    private Set<String> projectIdList = new HashSet<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (firstName.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.firstName = firstName;
        }

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        if (lastName.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.lastName = lastName;
        }

    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {

        if (sex.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
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
            System.out.println("Age value must be greater than zero");
        } else {
            this.age = age;
        }
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {

        if (jobTitle.matches(".*\\d.*")) {
            System.out.println("The jobTitle must contain only characters");
        } else {
            this.jobTitle = jobTitle;
        }
    }

    public Set<String> getProjectIdList() {
        return projectIdList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", id='" + getId() + '\'' +
                ", projectIdList=" + projectIdList +
                '}';
    }

}
