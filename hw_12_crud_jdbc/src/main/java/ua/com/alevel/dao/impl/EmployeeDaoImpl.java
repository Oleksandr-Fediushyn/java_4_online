package ua.com.alevel.dao.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@BeanClass
public class EmployeeDaoImpl implements EmployeeDao {

    @InjectBean
    private JdbcService jdbcService;

    private static final String CREATE_EMPLOYEE = "insert into employees values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "update employees set updated = CURRENT_TIMESTAMP(), first_name = ?, last_name = ?, sex = ?, age = ?, email = ?, job_title = ? where id = ?";
    private static final String FIND_EMPLOYEE_BY_ID = "select * from employees where id = ";
    private static final String FIND_EMPLOYEE = "select * from employees";
    private static final String FIND_ALL_EMPLOYEES_BY_PROJECT = "select * from employees left join project_emp project on project.emp_id = employees.id where project.project_id = ";

    private static final String DELETE_RELATION = "delete from project_emp where emp_id = ?";
    private static final String DELETE_EMPLOYEE = "delete from employees where id = ?";

    @Override
    public void create(Employee entity) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EMPLOYEE)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getSex());
            preparedStatement.setLong(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getJobTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }

    }

    @Override
    public void update(Employee entity) {
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getSex());
            preparedStatement.setLong(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getJobTitle());
            preparedStatement.setLong(7, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public void delete(Long id) {
        deleteRelation(id);
        Connection connection = this.jdbcService.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (ResultSet resultSet = this.jdbcService.getStatement().executeQuery(FIND_EMPLOYEE_BY_ID + id)) {
            if (resultSet.next()) {
                return Optional.of(generateEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = this.jdbcService.getStatement().executeQuery(FIND_EMPLOYEE)) {
            while (resultSet.next()) {
                employees.add(generateEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return employees;
        }
        return employees;
    }

    @Override
    public Collection<Employee> findEmployeeByProjectId(Long projectId) {
        List<Employee> employees = new ArrayList<>();
        try (ResultSet resultSet = this.jdbcService.getStatement().executeQuery(FIND_ALL_EMPLOYEES_BY_PROJECT + projectId)) {
            while (resultSet.next()) {
                employees.add(generateEmployeeByResultSet(resultSet));
            }
        } catch (SQLException e) {
            return employees;
        }
        return employees;
    }

    @Override
    public boolean isValidEmployeeData(Employee employee) {
        return employee.isValidEmployeeData(employee);
    }

    @Override
    public boolean existByEmail(String email) {
        Collection<Employee> employees = findAll();
        return employees.stream().anyMatch(employee -> employee.getEmail().equals(email));
    }

    private Employee generateEmployeeByResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String sex = resultSet.getString("sex");
        int age = resultSet.getInt("age");
        String email = resultSet.getString("email");
        String jobTitle = resultSet.getString("job_title");
        Employee employee = new Employee();
        employee.setId(id);
        employee.setCreated(created);
        employee.setUpdated(updated);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSex(sex);
        employee.setAge(age);
        employee.setEmail(email);
        employee.setJobTitle(jobTitle);
        return employee;
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
