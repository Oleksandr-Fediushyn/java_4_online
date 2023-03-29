package ua.com.alevel.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "location")
    private String location;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "project_emp",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    private Set<Employee> employees;

    public Project() {
        super();
        employees = new HashSet<>();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String name) {

        if (name.matches(".*\\d.*")) {
            System.out.println("The name must contain only characters");
        } else {
            this.projectName = name;
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

    @Override
    public String toString() {
        return "Project{" +
                " id=" + getId() + '\'' +
                ", created='" + getCreated() + '\'' +
                ", updated='" + getUpdated() + '\'' +
                ", name='" + projectName + '\'' +
                ", manager='" + managerName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public boolean isValidProjectData(Project project) {
        if (isValidName(project.getProjectName()) && isValidManagerName(project.getManagerName()) && isValidLocation(project.getLocation()))
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
