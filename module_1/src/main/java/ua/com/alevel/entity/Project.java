package ua.com.alevel.entity;

import java.util.HashSet;
import java.util.Set;

public class Project extends BaseEntity {

    private String name;
    private String managerName;
    private String location;
    private Set<String> employeeIdList = new HashSet<>();

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

    public Set<String> getEmployeeIdList() {
        return employeeIdList;
    }


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {

        if (managerName.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.managerName = managerName;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        if (location.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.location = location;
        }
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", manager='" + managerName + '\'' +
                ", location='" + location + '\'' +
                ", id=" + getId() +
                ", employeeIdList=" + employeeIdList +
                '}';
    }


}



