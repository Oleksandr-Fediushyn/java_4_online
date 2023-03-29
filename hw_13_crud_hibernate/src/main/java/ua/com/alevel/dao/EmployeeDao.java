package ua.com.alevel.dao;

import ua.com.alevel.entity.Employee;

import java.util.Collection;

public interface EmployeeDao extends BaseDao<Employee> {

    Collection<Employee> findEmployeeByProjectId(Long projectId);

    public boolean isValidEmployeeData(Employee employee);

    boolean existByEmail(String email);
}

