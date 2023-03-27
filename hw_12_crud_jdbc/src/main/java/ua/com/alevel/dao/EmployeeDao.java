package ua.com.alevel.dao;

import ua.com.alevel.entity.Employee;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDao {

    void create(Employee entity);

    void update(Employee entity);

    void delete(Long id);

    Optional<Employee> findById(Long id);

    Collection<Employee> findAll();

    Collection<Employee> findEmployeeByProjectId(Long projectId);

    public boolean isValidEmployeeData(Employee employee);

    boolean existByEmail(String email);
}

