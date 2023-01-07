package ua.com.alevel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EmployeeServiceTest {

    private static final EmployeeService employeeService = new EmployeeService();

    private static final int SIZE = 10;
    private static final String firstName = "testName";
    private static final String lastName = "testLastName";
    private static final String sex = "male";
    private static final Integer age = 22;
    private static final String email = "test@mail.com";
    private static final String jobTitle = "testProgrammer";

    private static final String name = "projectName";

    private static final String managerName = "managerName";
    private static final String location = "locationName";

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < SIZE; i++) {
            Employee employee = generateEmployee(i);
            employeeService.createEmployee(employee);
        }
    }

    @BeforeAll
    public static void setUpProject() {
        for (int i = 0; i < SIZE; i++) {
            Project project = generateProject(i);
            employeeService.createProject(project);
        }
    }


    @Test
    @Order(1)
    public void checkSizeEmployees() {
        Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE);
    }

    @Test
    @Order(2)
    public void createEmployee() {
       Employee employee = generateEmployee(SIZE + 1);
       employeeService.createEmployee(employee);
       Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE + 1);
    }

    @Test
    @Order(3)
    public void findEmployeeById(){
        Employee employee = employeeService.findAllEmployees().get(0);
        Assertions.assertEquals(employeeService.findEmployeeById(employee.getId()), employee);
    }


   @Test
   @Order(4)
    public void deleteEmployee() {
       Employee employee = employeeService.findAllEmployees().get(0);
       employeeService.deleteEmployee(employee.getId());
       Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE);
    }

    @Test
    @Order(5)
    public void shouldBeCreateEmployeeWhenEmailIsDuplicate() {
        Employee employee = employeeService.findAllEmployees().get(0);
        Employee newEmployee = new Employee();
        newEmployee.setEmail(employee.getEmail());
        employeeService.createEmployee(newEmployee);
        Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE);
    }

    @Test
    @Order(6)
    public void shouldBeCreateEmployeeWhenEmailIsValid() {
       Employee employeeWithValidEmail = generateOneEmployee();
       employeeService.createEmployee(employeeWithValidEmail);
       Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE+1);
    }

    @Test
    @Order(7)
    public void shouldBeCreateEmployeeWhenDataEmployeeIsValid() {
       Employee employeeWithValidData = generateOneEmployeeWithValidData();
       employeeService.createEmployee(employeeWithValidData);
       Assertions.assertEquals(employeeService.findAllEmployees().size(), SIZE+1);

    }

    @Test
    @Order(8)
    public void updateEmployeeTest() {
       Employee current = employeeService.findAllEmployees().get(0);
       current.setAge(35);
       employeeService.updateEmployee(current);
       Assertions.assertNotEquals(employeeService.findAllEmployees().get(0).getAge(), age);
    }

    @Test
    @Order(9)
    public void attachEmployeeToProject() {
        Project currentProject = null;
        for (int i = 0; i < SIZE; i++) {
            Employee attachEmployee = employeeService.findAllEmployees().get(i);
            currentProject = employeeService.findAllProjects().get(0);
            employeeService.attachEmployeeToProject(attachEmployee.getId(), currentProject.getId());
        }
        Assertions.assertEquals(employeeService.findEmployeeByProjectId(currentProject.getId()).size(), SIZE);

    }

    @Test
    @Order(10)
    public void findEmployeeByProjectId() {
        Project currentProject = employeeService.findAllProjects().get(0);

        Assertions.assertEquals(employeeService.findEmployeeByProjectId(currentProject.getId()).size(), SIZE);

    }

    @Test
    @Order(11)
    public void findProjectById(){
        Project project = employeeService.findAllProjects().get(0);
        Assertions.assertEquals(employeeService.findProjectById(project.getId()), project);
    }

    @Test
    @Order(12)
    public void deleteEmployeeFromProject() {
        Project currentProject = null;
        for (int i = 0; i < SIZE; i++) {
            Employee deleteEmployee = employeeService.findAllEmployees().get(i);
            currentProject = employeeService.findAllProjects().get(0);
            employeeService.deleteEmployeeFromProject(deleteEmployee.getId(), currentProject.getId());
        }
        Assertions.assertEquals(employeeService.findEmployeeByProjectId(currentProject.getId()).size(), 0);

    }

    @Test
    @Order(13)
    public void checkSizeProjects() {
        Assertions.assertEquals(employeeService.findAllProjects().size(), SIZE);
    }

    @Test
    @Order(14)
    public void createProject() {
       Project project = generateProject(SIZE + 1);
       employeeService.createProject(project);
       Assertions.assertEquals(employeeService.findAllProjects().size(), SIZE + 1);
    }


    @Test
    @Order(15)
    public void updateProjectTest() {
        Project project = employeeService.findAllProjects().get(0);
        project.setLocation("Kharkov");
        employeeService.updateProject(project);
        Assertions.assertNotEquals(employeeService.findAllProjects().get(0).getLocation(), location);
    }

    @Test
    @Order(16)
    public void deleteProject() {
        Project project = employeeService.findAllProjects().get(0);
        employeeService.deleteProject(project.getId());
        Assertions.assertEquals(employeeService.findAllProjects().size(), SIZE);
    }

    @Test
    @Order(17)
    public void shouldBeCreateProjectWhenDataEmployeeIsValid() {
        Project projectWithValidData = generateProject(SIZE +1);
        employeeService.createProject(projectWithValidData);
        Assertions.assertEquals(employeeService.findAllProjects().size(), SIZE+1);

    }


    private static Employee generateEmployee(int i) {
        Employee employee = new Employee();
        employee.setFirstName(firstName + (char)i);
        employee.setLastName(lastName + (char)i);
        employee.setSex(sex);
        employee.setAge(age + i);
        employee.setEmail("A" + i + email);
        employee.setJobTitle(jobTitle + (char)i);
        return employee;
    }

    private static Employee generateOneEmployee()
        {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSex(sex);
        employee.setAge(age);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle);
        return employee;
    }
    private static Employee generateOneEmployeeWithValidData()
    {
        Employee employee = new Employee();
        employee.setFirstName(firstName + "A");
        employee.setLastName(lastName + "A");
        employee.setSex(sex);
        employee.setAge(age +5);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle + "A");
        return employee;
    }

    private static Project generateProject(int i) {
        Project project = new Project();
        project.setName(name + (char)i);
        project.setManagerName(managerName + (char)i);
        project.setLocation(location +(char)i);

        return project;
    }

}
