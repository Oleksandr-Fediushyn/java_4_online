package ua.com.alevel.service;

import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;
import java.io.FileNotFoundException;
import java.util.Collection;

public interface EmployeeService {
    void createEmployee(Employee employee) throws FileNotFoundException;

    void updateEmployee(Employee employee);

    void deleteEmployee(String employeeId);

    Employee findEmployeeById(String id) throws FileNotFoundException;

    Collection<Employee> findAllEmployees();

    void createProject(Project project);

    void updateProject(Project project);

    void deleteProject(String projectId);

    Project findProjectById(String id);

    Collection<Employee> findEmployeeByProjectId(String projectId);

    Collection<Project> findAllProjects();

    void attachEmployeeToProject(String employeeId, String projectId);

    void deleteEmployeeFromProject(String employeeId, String projectId);
}
