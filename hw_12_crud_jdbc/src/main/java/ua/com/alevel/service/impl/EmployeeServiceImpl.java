package ua.com.alevel.service.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;
import java.util.Optional;

@BeanClass
public class EmployeeServiceImpl implements EmployeeService {

    @InjectBean
    private EmployeeDao employeeDao;

    @Override
    public void create(Employee entity) {
        if (!employeeDao.existByEmail(entity.getEmail()) && employeeDao.isValidEmployeeData(entity)) {
            employeeDao.create(entity);
        }
    }

    @Override
    public void update(Employee entity, Long id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if (optionalEmployee.isPresent()) {
            entity.setId(id);
            if (!employeeDao.existByEmail(entity.getEmail()) && employeeDao.isValidEmployeeData(entity)) {
                employeeDao.update(entity);
            }
        } else {
            System.out.println("Employee entry not found\n");
        }
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optionalEmployee = employeeDao.findById(id);
        if (optionalEmployee.isPresent()) {
            return employeeDao.findById(id).get();
        }
        return optionalEmployee.orElseThrow(() -> new RuntimeException("Employee entry not found\n"));
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Collection<Employee> findEmployeeByProjectId(Long projectId) {
        return employeeDao.findEmployeeByProjectId(projectId);
    }

}
