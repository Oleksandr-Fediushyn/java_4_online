package ua.com.alevel.dao.impl;


import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.dao.ProjectDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@BeanClass
public class ProjectDaoImpl implements ProjectDao {

    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_PROJECT = "insert into projects values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), ?, ?, ?)";
    private static final String UPDATE_PROJECT = "update projects set updated = CURRENT_TIMESTAMP(), project_name = ?, manager_name = ?, location = ? where id = ?";
    private static final String FIND_PROJECT_BY_ID = "select * from projects where id = ";
    private static final String FIND_PROJECT = "select * from projects";
    private static final String ATTACH_EMPLOYEE_TO_PROJECT = "insert into project_emp (project_id, emp_id) values ( ?, ?)";
    private static final String DELETE_EMPLOYEE_FROM_PROJECT = "delete from project_emp where project_id = ? and emp_id = ?";
    private static final String DELETE_RELATION = "delete from project_emp where project_id = ?";
    private static final String DELETE_PROJECT = "delete from projects where id = ?";

    @Override
    public void create(Project entity) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PROJECT)) {
            preparedStatement.setString(1, entity.getProjectName());
            preparedStatement.setString(2, entity.getManagerName());
            preparedStatement.setString(3, entity.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void update(Project entity) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROJECT)) {
            preparedStatement.setString(1, entity.getProjectName());
            preparedStatement.setString(2, entity.getManagerName());
            preparedStatement.setString(3, entity.getLocation());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void delete(Long id) {
        deleteRelation(id);
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROJECT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Optional<Project> findById(Long id) {
        try (ResultSet resultSet = this.jdbcService.getStatement().executeQuery(FIND_PROJECT_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(generateProjectByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        try (ResultSet resultSet = this.jdbcService.getStatement().executeQuery(FIND_PROJECT)) {
            while (resultSet.next()) {
                projects.add(generateProjectByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return projects;
        }
        return projects;
    }

    @Override
    public void attachEmployeeToProject(Long projectId, Long employeeId) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ATTACH_EMPLOYEE_TO_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.setLong(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void deleteEmployeeFromProject(Long projectId, Long employeeId) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_FROM_PROJECT)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.setLong(2, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public boolean isValidProjectData(Project project) {
        return project.isValidProjectData(project);
    }

    private Project generateProjectByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String projectName = resultSet.getString("project_name");
        String managerName = resultSet.getString("manager_name");
        String location = resultSet.getString("location");
        Project project = new Project();
        project.setId(id);
        project.setCreated(created);
        project.setUpdated(updated);
        project.setProjectName(projectName);
        project.setManagerName(managerName);
        project.setLocation(location);
        return project;
    }

    private void deleteRelation(Long id) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RELATION)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }
}
