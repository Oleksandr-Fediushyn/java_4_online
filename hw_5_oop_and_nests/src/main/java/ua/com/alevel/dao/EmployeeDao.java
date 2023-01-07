package ua.com.alevel.dao;

import ua.com.alevel.db.DbStorage;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.util.List;

public class EmployeeDao {
    DbStorage dbStorage = DbStorage.getInstance();

   public void createEmployee(Employee employee) {
        dbStorage.createEmployee(employee);

    }
   public void updateEmployee(Employee employee) {
       dbStorage.updateEmployee(employee);
    }

   public void deleteEmployee(String employeeId) {
        dbStorage.deleteEmployee(employeeId);
    }

   public Employee findEmployeeById(String id) {
        return  dbStorage.findEmployeeById(id).get();
    }

   public List<Employee> findAllEmployees() {
        return dbStorage.findAllEmployees();
    }

    public void createProject(Project project) {
        dbStorage.createProject(project);

    }
    public void updateProject(Project project) {
        dbStorage.updateProject(project);
    }

    public void deleteProject(String projectId) {
        dbStorage.deleteProject(projectId);
    }

    public List<Employee> findEmployeeByProjectId(String projectId)
    {
        return dbStorage.findEmployeeByProjectId(projectId);
    }

    public Project findProjectById(String id) {
        return  dbStorage.findProjectById(id).get();
    }
    public  List<Project> findAllProjects()
    {
        return dbStorage.findAllProjects();
    }

    public  void attachEmployeeToProject(String employeeId, String projectId)
    {
        dbStorage.attachEmployeeToProject(employeeId, projectId);

    }

    public  void deleteEmployeeFromProject(String employeeId, String projectId)
    {
        dbStorage.deleteEmployeeFromProject(employeeId, projectId);
    }

    public boolean existByEmail(String email) {
        return dbStorage.existByEmail(email);
    }

    public boolean isValidEmployeeData(Employee employee) {
        return dbStorage.isValidEmployeeData(employee);
    }

    public boolean isValidProjectData(Project project) {
        return dbStorage.isValidProjectData(project);
    }
}
