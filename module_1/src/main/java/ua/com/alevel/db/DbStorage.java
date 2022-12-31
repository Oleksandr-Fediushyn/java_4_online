package ua.com.alevel.db;

import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DbStorage {

    private static List<Employee> employees = new ArrayList<>();
    private static List<Project> projects = new ArrayList<>();

    private DbStorage() { }

    public static void addEmployee(Employee employee) {
        employee.setId(generateEmployeeId());
        employees.add(employee);
    }

    public static void deleteEmployee(String employeeId) {
        Employee employee = getEmployee(employeeId);
        Set<String> projectIds = employee.getProjectIdList();
        for (String projectId : projectIds) {
            deleteEmployeeFromProject(employeeId,projectId);
        }

        employees.remove(employee);
    }

    private static String generateEmployeeId() {
        String id = UUID.randomUUID().toString();
        if (employees.stream().anyMatch(employee -> employee.getId().equals(id))) {
            return generateEmployeeId();
        }
        return id;
    }
    private static String generateProjectId() {
        String id = UUID.randomUUID().toString();
        if (projects.stream().anyMatch(project -> project.getId().equals(id))) {
            return generateProjectId();
        }
        return id;
    }

    public static Employee getEmployee(String id) {
        return employees.
                stream().
                filter(employee -> employee.getId().equals(id)).
                findFirst()
                .get();
    }

    public static List<Employee> findAllEmployees() {
        return employees;
    }

    public static void addProject(Project project) {
        project.setId(generateProjectId());
        projects.add(project);
    }

    public static void deleteProject(String projectId) {
        Project project = getProject(projectId);
        Set<String> employeeIds = project.getEmployeeIdList();
        for (String employeeId : employeeIds) {
            deleteEmployeeFromProject(employeeId,projectId);
        }
        projects.remove(project);
    }
    public static Project getProject(String id) {
        return projects.
                stream().
                filter(project -> project.getId().equals(id)).
                findFirst()
                .get();
    }

    public static List<Project> findAllProjects() {
        return projects;
    }

    public static void addEmployeeToProject(String employeeId, String projectId) {
        Project project = getProject(projectId);
        Set<String> employees = project.getEmployeeIdList();
        employees.add(employeeId);
        Employee employee = getEmployee(employeeId);
        Set<String> projects = employee.getProjectIdList();
        projects.add(projectId);

    }

    public static void deleteEmployeeFromProject(String employeeId, String projectId) {
        Project project = getProject(projectId);
        Set<String> employees = project.getEmployeeIdList();
        employees.remove(employeeId);
        Employee employee = getEmployee(employeeId);
        Set<String> projects = employee.getProjectIdList();
        projects.remove(projectId);

    }

    public static List<Employee> findByProject(String projectId) {
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
}
