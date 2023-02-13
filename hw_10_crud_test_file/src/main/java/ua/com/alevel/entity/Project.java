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

    public Set<String> getEmployeeIdList() {
        return employeeIdList;
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

    public boolean isValidProjectData(Project project) {
        if (isValidName(project.getName()) && isValidManagerName(project.getManagerName()) && isValidLocation(project.getLocation()))
            return true;
        return false;
    }

    public boolean isValidName(String name) {
        if (name == "") {
            System.out.println("You not input information about name project");
            return false;
        }

        if (name.matches(".*\\d.*")) {
            System.out.println("The field name must contain only characters");
            return false;
        }
        return true;
    }

    public boolean isValidManagerName(String managerName) {
        if (managerName == "") {
            System.out.println("You not input information about managerName");
            return false;
        }

        if (managerName.matches(".*\\d.*")) {
            System.out.println("The field managerName must contain only characters");
            return false;
        }
        return true;
    }

    public boolean isValidLocation(String location) {
        if (location == "") {
            System.out.println("You not input information about location");
            return false;
        }

        if (location.matches(".*\\d.*")) {
            System.out.println("The field location must contain only characters");
            return false;
        }
        return true;
    }


}

