package ua.com.alevel.entity;

import jakarta.persistence.*;
import ua.com.alevel.employee_gender.EmployeeGender;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private EmployeeGender sex;

    @Column()
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "job_title")
    private String jobTitle;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.PERSIST)
    private Set<Project> projects;

    public Employee() {
        super();
        projects = new HashSet<>();
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (isValidFirstName(firstName)) {

            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (isValidLastName(lastName)) {

            this.lastName = lastName;
        }
    }

    public EmployeeGender getSex() {
        return sex;
    }

    public void setSex(EmployeeGender sex) {
        if (isValidSex(sex)) {
            this.sex = sex;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (isValidAge(age)) {
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        }
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        if (isValidJobTitle(jobTitle)) {
            this.jobTitle = jobTitle;
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                " id='" + getId() + '\'' +
                ", created='" + getCreated() + '\'' +
                ", updated='" + getUpdated() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }

    public boolean isValidEmployeeData(Employee employee) {
        if (isValidFirstName(employee.getFirstName()) && isValidLastName(employee.getLastName()) && isValidJobTitle(employee.getJobTitle()) && isValidSex(employee.getSex()) && isValidAge(employee.getAge()) && isValidEmail(employee.getEmail()))
            return true;
        return false;
    }

    public boolean isValidFirstName(String firstName) {
        if (firstName == "") {
            System.out.println("You not input information about firstName");
            return false;
        }

        if (firstName.matches(".*\\d.*")) {
            System.out.println("The field firstName must contain only characters");
            return false;
        }
        return true;
    }

    public boolean isValidLastName(String lastName) {
        if (lastName == "") {
            System.out.println("You not input information about lastName");
            return false;
        }

        if (lastName.matches(".*\\d.*")) {
            System.out.println("The field lastName must contain only characters");
            return false;
        }
        return true;
    }

    public boolean isValidJobTitle(String jobTitle) {
        if (jobTitle == "") {
            System.out.println("You not input information about jobTitle");
            return false;
        }

        if (jobTitle.matches(".*\\d.*")) {
            System.out.println("The field jobTitle must contain only characters");
            return false;
        }
        return true;
    }

    public boolean isValidSex(EmployeeGender sex) {
        if (Objects.equals(sex.name(), "")) {
            System.out.println("You not input information about sex");
            return false;
        }
        boolean result = false;
        for (EmployeeGender gender : EmployeeGender.values()) {
            if (gender.equals(sex)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isValidAge(Integer age) {
        if (age < 0) {
            System.out.println("Age value must be greater than zero");
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        String emailCheck = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" +
                "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        if (email.matches(emailCheck)) {
            return true;
        }
        System.out.println("The field email contains uncorrected characters");
        return false;
    }

}
