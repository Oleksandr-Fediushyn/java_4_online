package ua.com.alevel.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.io.*;
import java.util.*;

public class EmployeeDaoJson implements EmployeeDao {
    String pathEmployees = "employees.json";
    String pathProjects = "projects.json";

    public EmployeeDaoJson() {
        createJsonFiles();
    }

    private void createJsonFiles() {

        File fileEmployees = new File(pathEmployees);
        try {
            if (!fileEmployees.exists()) {
                fileEmployees.createNewFile();
                FileWriter writeEmp = new FileWriter(pathEmployees);
                writeEmp.write("{ \"employees\": [ ] }");
                writeEmp.flush();
                writeEmp.close();
            }

            File fileProjects = new File(pathProjects);
            if (!fileProjects.exists()) {
                fileProjects.createNewFile();
                FileWriter writeProj = new FileWriter(pathProjects);
                writeProj.write("{ \"projects\": [ ] }");
                writeProj.flush();
                writeProj.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void createEmployee(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            EmployeeContainer container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = container.getEmployees();
            employees.add(employee);
            container.setEmployees(employees);
            String json = gson.toJson(container);
            FileWriter fileWriter = new FileWriter(pathEmployees);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployee(String id) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = container.getEmployees();
            return employees
                    .stream()
                    .filter(employee -> employee.getId().equals(id))
                    .findFirst().get();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = container.getEmployees();
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getId().equals(employee.getId())) {
                    employees.set(i, employee);
                }
            }
            container.setEmployees(employees);
            String json = gson.toJson(container);
            FileWriter fileWriter = new FileWriter(pathEmployees);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            EmployeeContainer containerEmp = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = containerEmp.getEmployees();
            Employee employeeDel = employees
                    .stream()
                    .filter(employee -> employee.getId().equals(employeeId))
                    .findFirst().get();
            Set<String> projectIds = employeeDel.getProjectIdList();
            for (String projectId : projectIds) {
                deleteEmployeeFromProject(employeeId, projectId);
            }
            employees.remove(employeeDel);
            containerEmp.setEmployees(employees);
            String json = gson.toJson(containerEmp);
            FileWriter fileWriter = new FileWriter(pathEmployees);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = container.getEmployees();
            return employees
                    .stream()
                    .filter(employee -> employee.getId().equals(id))
                    .findFirst();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            return container.getEmployees();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createProject(Project project) {

        project.setId(UUID.randomUUID().toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ProjectContainer container = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = container.getProjects();
            projects.add(project);
            container.setProjects(projects);
            String json = gson.toJson(container);
            FileWriter fileWriter = new FileWriter(pathProjects);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateProject(Project project) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ProjectContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = container.getProjects();
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getId().equals(project.getId())) {
                    projects.set(i, project);
                }
            }
            container.setProjects(projects);
            String json = gson.toJson(container);
            FileWriter fileWriter = new FileWriter(pathProjects);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProject(String projectId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ProjectContainer containerProj = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = containerProj.getProjects();
            Project projectFiltered = projects
                    .stream()
                    .filter(project -> project.getId().equals(projectId))
                    .findFirst().get();
            Set<String> employeeIds = projectFiltered.getEmployeeIdList();
            for (String employeeId : employeeIds) {
                deleteEmployeeFromProject(employeeId, projectId);
            }
            projects.remove(projectFiltered);
            containerProj.setProjects(projects);
            String json = gson.toJson(containerProj);
            FileWriter fileWriter = new FileWriter(pathProjects);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Project> findProjectById(String id) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ProjectContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = container.getProjects();
            return projects
                    .stream()
                    .filter(project -> project.getId().equals(id))
                    .findFirst();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Employee> findEmployeeByProjectId(String projectId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ProjectContainer containerProj = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = containerProj.getProjects();
            Project projectFiltered = projects
                    .stream()
                    .filter(project -> project.getId().equals(projectId))
                    .findFirst().get();
            Set<String> employeesIds = projectFiltered.getEmployeeIdList();
            List<Employee> employees = new ArrayList<>();
            for (String employeeId : employeesIds) {
                Employee employee = getEmployee(employeeId);
                if (employee != null) {
                    employees.add(employee);
                }
            }
            return employees;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Project> findAllProjects() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ProjectContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            return container.getProjects();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void attachEmployeeToProject(String employeeId, String projectId) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ProjectContainer containerProj = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = containerProj.getProjects();
            for (Project project : projects) {
                if (project.getId().equals(projectId)) {
                    project.getEmployeeIdList().add(employeeId);
                }
            }
            containerProj.setProjects(projects);
            String json = gson.toJson(containerProj);
            FileWriter fileWriter = new FileWriter(pathProjects);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
            EmployeeContainer containerEmp = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = containerEmp.getEmployees();
            for (Employee employee : employees) {
                if (employee.getId().equals(employeeId)) {
                    employee.getProjectIdList().add(projectId);
                }
            }
            containerEmp.setEmployees(employees);
            String jsonEmp = gson.toJson(containerEmp);
            FileWriter fileWriterEmp = new FileWriter(pathEmployees);
            fileWriterEmp.write(jsonEmp);
            fileWriterEmp.flush();
            fileWriterEmp.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployeeFromProject(String employeeId, String projectId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ProjectContainer containerProj = gson.fromJson(new FileReader(pathProjects), ProjectContainer.class);
            List<Project> projects = containerProj.getProjects();
            for (Project project : projects) {
                if (project.getId().equals(projectId)) {
                    project.getEmployeeIdList().remove(employeeId);
                }
            }
            containerProj.setProjects(projects);
            String json = gson.toJson(containerProj);
            FileWriter fileWriter = new FileWriter(pathProjects);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
            EmployeeContainer containerEmp = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = containerEmp.getEmployees();
            for (Employee employee : employees) {
                if (employee.getId().equals(employeeId)) {
                    employee.getProjectIdList().remove(projectId);
                }
            }
            containerEmp.setEmployees(employees);
            String jsonEmp = gson.toJson(containerEmp);
            FileWriter fileWriterEmp = new FileWriter(pathEmployees);
            fileWriterEmp.write(jsonEmp);
            fileWriterEmp.flush();
            fileWriterEmp.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class EmployeeContainer {
        private List<Employee> employees;
        public List<Employee> getEmployees() {
            return employees;
        }
        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
    private static class ProjectContainer {
        private List<Project> projects;
        public List<Project> getProjects() {
            return projects;
        }
        public void setProjects(List<Project> projects) {
            this.projects = projects;
        }
    }
    @Override
    public boolean existByEmail(String email) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EmployeeContainer container = null;
        try {
            container = gson.fromJson(new FileReader(pathEmployees), EmployeeContainer.class);
            List<Employee> employees = container.getEmployees();
            return employees.stream().anyMatch(employee -> employee.getEmail().equals(email));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean isValidEmployeeData(Employee employee) {
        return employee.isValidEmployeeData(employee);
    }
    @Override
    public boolean isValidProjectData(Project project) {
        return project.isValidProjectData(project);
    }
}
