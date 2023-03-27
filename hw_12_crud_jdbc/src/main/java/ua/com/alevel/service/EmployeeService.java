package ua.com.alevel.service;

import ua.com.alevel.entity.Employee;

import java.util.Collection;

public interface EmployeeService {

    void create(Employee entity);

    void update(Employee entity, Long id);

    void delete(Long id);

    Employee findById(Long id);

    Collection<Employee> findAll();

    Collection<Employee> findEmployeeByProjectId(Long projectId);

}

