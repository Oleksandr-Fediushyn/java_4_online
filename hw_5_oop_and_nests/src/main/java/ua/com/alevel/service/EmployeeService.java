package ua.com.alevel.service;

import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Project;
import ua.com.alevel.entity.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDao();


    public void createEmployee(Employee employee) {
        if (!existByEmail(employee.getEmail())  && isValidEmployeeData(employee)) {
            employeeDao.createEmployee(employee);
        }
    }
    public void updateEmployee(Employee employee)  {

        employeeDao.updateEmployee(employee);

    }

    public void deleteEmployee(String employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }

    public Employee findEmployeeById(String id) {
        return  employeeDao.findEmployeeById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    public void createProject(Project project) {
        if (isValidProjectData(project)){
        employeeDao.createProject(project);}
    }
    public void updateProject(Project project) {

        employeeDao.updateProject(project);
    }

    public void deleteProject(String projectId) {
        employeeDao.deleteProject(projectId);
    }

    public Project findProjectById(String id) {
        return  employeeDao.findProjectById(id);
    }


    public List<Employee> findEmployeeByProjectId(String projectId)
    {
        return employeeDao.findEmployeeByProjectId(projectId);
    }
    public  List<Project> findAllProjects()
    {
        return employeeDao.findAllProjects();
    }

    public  void attachEmployeeToProject(String employeeId, String projectId)
    {
        employeeDao.attachEmployeeToProject(employeeId, projectId);

    }

    public  void deleteEmployeeFromProject(String employeeId, String projectId)
    {
        employeeDao.deleteEmployeeFromProject(employeeId, projectId);
    }

    private boolean existByEmail(String email) {
        return employeeDao.existByEmail(email);
    }

    private boolean isValidEmployeeData(Employee employee) {
        return employeeDao.isValidEmployeeData(employee);
    }

    private boolean isValidProjectData(Project project) {
        return employeeDao.isValidProjectData(project);
    }
}
