package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CrudController;
import ua.com.alevel.employee_gender.EmployeeGender;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.ProjectService;
import ua.com.alevel.service.impl.EmployeeServiceImpl;
import ua.com.alevel.service.impl.ProjectServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class CrudControllerImpl implements CrudController {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private final ProjectService projectService = new ProjectServiceImpl();

    @Override
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
            case "1":
                createEmployee(reader);
                break;
            case "2":
                findEmployeeById(reader);
                break;
            case "3":
                findAllEmployees();
                break;
            case "4":
                updateEmployee(reader);
                break;
            case "5":
                deleteEmployee(reader);
                break;
            case "6":
                createProject(reader);
                break;
            case "7":
                findProjectById(reader);
                break;
            case "8":
                findAllProjects();
                break;
            case "9":
                updateProject(reader);
                break;
            case "10":
                deleteProject(reader);
                break;
            case "11":
                attachEmployeeToProject(reader);
                break;
            case "12":
                deleteEmployeeFromProject(reader);
                break;
            case "13":
                findEmployeeByProjectId(reader);
                break;
            case "14":
                stop();
                break;
        }
        menu();
    }

    private void createEmployee(BufferedReader reader) throws IOException {
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
        employee.setSex(EmployeeGender.valueOf(sex));
        employee.setAge(age);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle);
        employeeService.create(employee);
    }

    private void findEmployeeById(BufferedReader reader) throws IOException {
        System.out.println("Find employee by id");
        System.out.println("Please enter id");
        String employeeId = reader.readLine();
        if (employeeId.equals("")) {
            System.out.println("You do not enter employee id\n");
            return;
        }
        Long id = Long.parseLong(employeeId);
        Employee employee = employeeService.findById(id);
        System.out.println("employee = " + employee);
    }

    private void findAllEmployees() {
        System.out.println("Find all employees");
        Collection<Employee> employees = employeeService.findAll();
        System.out.println("employees = " + employees);
    }

    private void updateEmployee(BufferedReader reader) throws IOException {
        System.out.println("Update information about an employee");
        System.out.println("Please enter employee id");
        Long employeeId = Long.parseLong(reader.readLine());
        Employee employee = new Employee();
        System.out.println("Please enter new first name");
        String firstName = reader.readLine();
        employee.setFirstName(firstName);
        System.out.println("Please enter new last name");
        String lastName = reader.readLine();
        employee.setLastName(lastName);
        System.out.println("Please enter sex");
        String sex = reader.readLine();
        employee.setSex(EmployeeGender.valueOf(sex));
        System.out.println("Please enter new age");
        Integer age = Integer.parseInt(reader.readLine());
        employee.setAge(age);
        System.out.println("Please enter new email");
        String email = reader.readLine();
        employee.setEmail(email);
        System.out.println("Please enter new job title");
        String jobTitle = reader.readLine();
        employee.setJobTitle(jobTitle);
        employee.setUpdated(new Date());
        employeeService.update(employee, employeeId);
    }

    private void deleteEmployee(BufferedReader reader) throws IOException {
        System.out.println("Delete employee");
        System.out.println("Please enter employee id");
        Long employeeId = Long.parseLong(reader.readLine());
        Employee employee = employeeService.findById(employeeId);
        if (employee != null) {
            Set<Project> projects = employee.getProjects();
            for (Project project : projects) {
                Set<Employee> employees = project.getEmployees();
                System.out.println("employees before " + project.getEmployees());
                employees.remove(employee);
                projectService.update(project, project.getId());
                System.out.println("employees after " + project.getEmployees());
            }
            employeeService.delete(employee);
        } else {
            System.out.println("Employee do not found");
        }
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
        project.setProjectName(name);
        project.setManagerName(managerName);
        project.setLocation(location);
        projectService.create(project);
    }

    private void deleteProject(BufferedReader reader) throws IOException {
        System.out.println("Delete project");
        System.out.println("Please enter project id");
        Long projectId = Long.parseLong(reader.readLine());
        Project project = projectService.findById(projectId);
        if (project != null) {
            Set<Employee> employees = project.getEmployees();
            for (Employee employee : employees) {
                Set<Project> projects = employee.getProjects();
                projects.remove(project);
                employeeService.update(employee, employee.getId());
            }
            projectService.delete(project);
        } else {
            System.out.println("Project do not found");
        }
    }

    private void attachEmployeeToProject(BufferedReader reader) throws IOException {
        System.out.println("Attach employee to project");
        System.out.println("Please enter project id");
        Long projectId = Long.parseLong(reader.readLine());
        System.out.println("Please enter employee id");
        Long employeeId = Long.parseLong(reader.readLine());
        Project project = projectService.findById(projectId);
        Employee employee = employeeService.findById(employeeId);
        Set<Employee> employees = project.getEmployees();
        employees.add(employee);
        Set<Project> projects = employee.getProjects();
        projects.add(project);
        employeeService.update(employee, employeeId);
        projectService.update(project, projectId);
    }

    private void findProjectById(BufferedReader reader) throws IOException {
        System.out.println("Find project by id");
        System.out.println("Please enter id");
        String projectId = reader.readLine();
        if (projectId.equals("")) {
            System.out.println("You do not enter project id\n");
            return;
        }
        Long id = Long.parseLong(projectId);
        Project project = projectService.findById(id);
        System.out.println("project = " + project);
    }

    private void deleteEmployeeFromProject(BufferedReader reader) throws IOException {
        System.out.println("Delete employee from project");
        System.out.println("Please enter project id");
        Long projectId = Long.parseLong(reader.readLine());
        System.out.println("Please enter employee id");
        Long employeeId = Long.parseLong(reader.readLine());
        Project project = projectService.findById(projectId);
        Employee employee = employeeService.findById(employeeId);
        Set<Employee> employees = project.getEmployees();
        employees.remove(employee);
        projectService.update(project, projectId);
    }

    private void findEmployeeByProjectId(BufferedReader reader) throws IOException {
        System.out.println("find all employees by project");
        System.out.println("Please enter project id");
        Long projectId = Long.parseLong(reader.readLine());
        Collection<Employee> employees = employeeService.findEmployeeByProjectId(projectId);
        System.out.println("employees = " + employees);
    }

    private void findAllProjects() {
        System.out.println("projects = " + projectService.findAll());

    }

    private void updateProject(BufferedReader reader) throws IOException {
        System.out.println("Update information about a project");
        System.out.println("Please enter project id");
        Long projectId = Long.parseLong(reader.readLine());
        Project project = new Project();
        System.out.println("Please enter new project's name");
        String name = reader.readLine();
        project.setProjectName(name);
        System.out.println("Please enter a new project manager name");
        String managerName = reader.readLine();
        project.setManagerName(managerName);
        System.out.println("Please enter a new project location");
        String location = reader.readLine();
        project.setLocation(location);
        project.setUpdated(new Date());
        projectService.update(project, projectId);
    }

    private void stop() {
        System.exit(0);
    }
}
