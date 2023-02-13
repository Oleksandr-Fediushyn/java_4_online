package ua.com.alevel.service;

import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.dao.EmployeeDaoJson;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.util.Collection;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoJson();

    @Override
    public void createEmployee(Employee employee) {
        if (!existByEmail(employee.getEmail()) && isValidEmployeeData(employee)) {
            employeeDao.createEmployee(employee);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeDao.deleteEmployee(employeeId);
    }

    @Override
    public Employee findEmployeeById(String id) {
        Optional<Employee> findEmployee = employeeDao.findEmployeeById(id);
        if (findEmployee.isPresent()) {
            return findEmployee.get();
        } else {
            System.out.println("Employee entry not found\n");
            return null;
        }
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    @Override
    public void createProject(Project project) {
        if (isValidProjectData(project)) {
            employeeDao.createProject(project);
        }
    }

    @Override
    public void updateProject(Project project) {
        employeeDao.updateProject(project);
    }

    @Override
    public void deleteProject(String projectId) {
        employeeDao.deleteProject(projectId);
    }

    @Override
    public Project findProjectById(String id) {
        Optional<Project> findProject = employeeDao.findProjectById(id);
        if (findProject.isPresent()) {
            return findProject.get();
        } else {
            System.out.println("Project entry not found\n");
            return null;
        }
    }

    @Override
    public Collection<Employee> findEmployeeByProjectId(String projectId) {
        return employeeDao.findEmployeeByProjectId(projectId);
    }

    @Override
    public Collection<Project> findAllProjects() {
        return employeeDao.findAllProjects();
    }

    @Override
    public void attachEmployeeToProject(String employeeId, String projectId) {
        employeeDao.attachEmployeeToProject(employeeId, projectId);
    }

    @Override
    public void deleteEmployeeFromProject(String employeeId, String projectId) {
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
