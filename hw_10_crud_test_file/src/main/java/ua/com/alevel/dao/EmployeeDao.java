package ua.com.alevel.dao;

import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Optional;

public interface EmployeeDao {

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(String employeeId);

    Optional<Employee> findEmployeeById(String id);

    Collection<Employee> findAllEmployees();

    void createProject(Project project);

    void updateProject(Project project);

    void deleteProject(String projectId);

    Optional<Project> findProjectById(String id);

    Collection<Employee> findEmployeeByProjectId(String projectId);

    Collection<Project> findAllProjects();

    void attachEmployeeToProject(String employeeId, String projectId);

    void deleteEmployeeFromProject(String employeeId, String projectId);

    boolean isValidProjectData(Project project);

    boolean isValidEmployeeData(Employee employee);

    boolean existByEmail(String email);
}
