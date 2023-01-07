package ua.com.alevel.controller;

import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;
import ua.com.alevel.service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;



public class EmployeeController {

    EmployeeService service = new EmployeeService();

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
        System.out.println("If you want create employee, please enter 1");
        System.out.println("If you want find employee, please enter 2");
        System.out.println("If you want find all employees, please enter 3");
        System.out.println("If you want to update information about an employee, please enter 4");
        System.out.println("If you want delete employee, please enter 5");
        System.out.println("If you want create project, please enter 6");
        System.out.println("If you want find project, please enter 7");
        System.out.println("If you want find all projects, please enter 8");
        System.out.println("If you want to update information about a project, please enter 9");
        System.out.println("If you want delete project, please enter 10");
        System.out.println("If you want attach employee to project, please enter 11");
        System.out.println("If you want delete employee from project, please enter 12");
        System.out.println("If you want find employees by project, please enter 13");
        System.out.println("If you want close application, please enter 14");
        System.out.println();
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" : create(reader); break;
            case "2" : findEmployeeById(reader); break;
            case "3" : findAllEmployees(); break;
            case "4" : updateEmployee(reader); break;
            case "5" : deleteEmployee(reader); break;
            case "6" : createProject(reader); break;
            case "7" : findProjectById(reader); break;
            case "8" : findAllProjects(); break;
            case "9" : updateProject(reader); break;
            case "10" : deleteProject(reader); break;
            case "11" : attachEmployeeToProject(reader); break;
            case "12" : deleteEmployeeFromProject(reader); break;
            case "13" : findEmployeeByProjectId(reader); break;
            case "14" : stop(); break;
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Create employee");
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter sex");
        String sex = reader.readLine();
        System.out.println("Please enter age");
        Integer age = Integer.parseInt(reader.readLine());
        System.out.println("Please enter email");
        String email = reader.readLine();
        System.out.println("Please enter job title");
        String jobTitle = reader.readLine();

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSex(sex);
        employee.setAge(age);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle);
        service.createEmployee(employee);
    }

    private void findEmployeeById(BufferedReader reader) throws IOException {
        System.out.println("Find employee by id");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Employee employee = service.findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee id does not exist\n");
        }
        System.out.println("employee = " + employee);
    }

    private void findAllEmployees() {
        System.out.println("Find all employees");
        List<Employee> employees = service.findAllEmployees();
        System.out.println("employees = " + employees);
    }

    private void updateEmployee(BufferedReader reader) throws IOException {
        System.out.println("Update information about an employee");
        System.out.println("Please enter employee id");
        String employeeId = reader.readLine();
        Employee employee = service.findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee id does not exist\n");
        }
        else {
        System.out.println("Please enter new first name");
        String firstName = reader.readLine();
        employee.setFirstName(firstName);
        System.out.println("Please enter new last name");
        String lastName = reader.readLine();
        employee.setLastName(lastName);
        System.out.println("Please enter sex");
        String sex = reader.readLine();
        employee.setSex(sex);
        System.out.println("Please enter new age");
        Integer age = Integer.parseInt(reader.readLine());
        employee.setAge(age);
        System.out.println("Please enter new email");
        String email = reader.readLine();
        employee.setEmail(email);
        System.out.println("Please enter new job title");
        String jobTitle = reader.readLine();
        employee.setJobTitle(jobTitle);
        service.updateEmployee(employee);}

    }

    private void createProject(BufferedReader reader) throws IOException {
        System.out.println("Create project");
        System.out.println("Please enter name");
        String name = reader.readLine();
        System.out.println("Please enter a project manager name");
        String managerName = reader.readLine();
        System.out.println("Please enter a project location");
        String location = reader.readLine();
        Project project = new Project();
        project.setName(name);
        project.setManagerName(managerName);
        project.setLocation(location);
        service.createProject(project);
    }

    private void deleteProject(BufferedReader reader) throws IOException {
        System.out.println("Delete project");
        System.out.println("Please enter project id");
        String projectId = reader.readLine();
        service.deleteProject(projectId);
    }

    private void attachEmployeeToProject(BufferedReader reader) throws IOException {
        System.out.println("Attach employee to project");
        System.out.println("Please enter project id");
        String projectId = reader.readLine();
        System.out.println("Please enter employee id");
        String employeeId = reader.readLine();
        service.attachEmployeeToProject(employeeId, projectId);
    }
    private void findProjectById(BufferedReader reader) throws IOException {
        System.out.println("Find project by id");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Project project = service.findProjectById(id);
        if (project == null) {
            System.out.println("Project id does not exist\n");
        }
        System.out.println("project = " + project);
    }

    private void deleteEmployeeFromProject(BufferedReader reader) throws IOException {
        System.out.println("Delete employee from project");
        System.out.println("Please enter project id");
        String projectId = reader.readLine();
        System.out.println("Please enter employee id");
        String employeeId = reader.readLine();
        service.deleteEmployeeFromProject(employeeId, projectId);
    }
    private void findEmployeeByProjectId(BufferedReader reader) throws IOException {
        System.out.println("find all employees by project");
        System.out.println("Please enter project id");
        String projectId = reader.readLine();
        List<Employee> employees = service.findEmployeeByProjectId(projectId);
        System.out.println("employees = " + employees);
    }

    private void findAllProjects() {
        System.out.println("projects = " + service.findAllProjects());

    }

    private void updateProject(BufferedReader reader) throws IOException {
        System.out.println("Update information about a project");
        System.out.println("Please enter project id");
        String projectId = reader.readLine();
        Project project = service.findProjectById(projectId);

        if (project == null) {
            System.out.println("Project id does not exist\n");
        }
        else {
        System.out.println("Please enter new project's name");
        String name = reader.readLine();
        project.setName(name);
        System.out.println("Please enter a new project manager name");
        String managerName = reader.readLine();
        project.setManagerName(managerName);
        System.out.println("Please enter a new project location");
        String location = reader.readLine();
        project.setLocation(location);
        service.updateProject(project);}

    }
    private void deleteEmployee(BufferedReader reader) throws IOException {
        System.out.println("Delete employee");
        System.out.println("Please enter employee id");
        String employeeId = reader.readLine();
        service.deleteEmployee(employeeId);
    }

    private void stop() {
        System.exit(0);
    }

}
