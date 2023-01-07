package ua.com.alevel.db;

import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbStorage {

    private  List<Employee> employees = new ArrayList<>();
    private  List<Project> projects = new ArrayList<>();


    private static DbStorage instance;

    private DbStorage() { }

    public static DbStorage getInstance() {
        if (instance == null) {
            instance = new DbStorage();
        }
        return instance;
    }

    public  void createEmployee(Employee employee) {
        employee.setId(generateEmployeeId());
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = findEmployeeById(employee.getId());
        if (optionalEmployee.isPresent()) {
            Employee current = optionalEmployee.get();
            current = employee;
        }
    }

    public void deleteEmployee(String employeeId) {
        Employee employee = getEmployee(employeeId);
        Set<String> projectIds = employee.getProjectIdList();
        for (String projectId : projectIds) {
            deleteEmployeeFromProject(employeeId,projectId);
        }

        employees.remove(employee);
    }

    private String generateEmployeeId() {
        String id = UUID.randomUUID().toString();
        if (employees.stream().anyMatch(employee -> employee.getId().equals(id))) {
            return generateEmployeeId();
        }
        return id;
    }
    private  String generateProjectId() {
        String id = UUID.randomUUID().toString();
        if (projects.stream().anyMatch(project -> project.getId().equals(id))) {
            return generateProjectId();
        }
        return id;
    }

    public  Employee getEmployee(String id) {
        return employees.
                stream().
                filter(employee -> employee.getId().equals(id)).
                findFirst()
                .get();
    }

    public  List<Employee> findAllEmployees() {
        return employees;
    }

    public  void createProject(Project project) {
        project.setId(generateProjectId());
        projects.add(project);
    }

    public  void deleteProject(String projectId) {
        Project project = getProject(projectId);
        Set<String> employeeIds = project.getEmployeeIdList();
        for (String employeeId : employeeIds) {
            deleteEmployeeFromProject(employeeId,projectId);
        }
        projects.remove(project);
    }
    public void updateProject(Project project) {
        Optional<Project> optionalProject = findProjectById(project.getId());
        if (optionalProject.isPresent()) {
            Project current = optionalProject.get();
            current = project;
        }
    }

    public Project getProject(String id) {
        return projects.
                stream().
                filter(project -> project.getId().equals(id)).
                findFirst()
                .get();
    }

    public  List<Project> findAllProjects() {
        return projects;
    }

    public  void attachEmployeeToProject(String employeeId, String projectId) {
        Project project = getProject(projectId);
        Set<String> employees = project.getEmployeeIdList();
        employees.add(employeeId);
        Employee employee = getEmployee(employeeId);
        Set<String> projects = employee.getProjectIdList();
        projects.add(projectId);

    }

    public  void deleteEmployeeFromProject(String employeeId, String projectId) {
        Project project = getProject(projectId);
        Set<String> employees = project.getEmployeeIdList();
        employees.remove(employeeId);
        Employee employee = getEmployee(employeeId);
        Set<String> projects = employee.getProjectIdList();
        projects.remove(projectId);

    }

    public Optional<Employee> findEmployeeById(String id) {
        return employees
                .stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }
    public Optional<Project> findProjectById(String id) {
        return projects
                .stream()
                .filter(project -> project.getId().equals(id))
                .findFirst();
    }

    public  List<Employee> findEmployeeByProjectId(String projectId) {
        Project project = getProject(projectId);
        Set<String> employeesIds = project.getEmployeeIdList();
        List<Employee> employees = new ArrayList<>();
        for (String employeeId : employeesIds) {
            Employee employee = getEmployee(employeeId);
            if (employee != null) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public boolean existByEmail(String email) {
        return employees.stream().anyMatch(employee -> employee.getEmail().equals(email));
    }


    public boolean isValidEmployeeData(Employee employee) {
        return employee.isValidEmployeeData(employee);
    }

    public boolean isValidProjectData(Project project) {
        return project.isValidProjectData(project);
    }
}
