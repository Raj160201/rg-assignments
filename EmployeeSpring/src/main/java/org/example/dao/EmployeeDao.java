package org.example.dao;

import org.example.model.Employee;
import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    Employee findById(int id);
    List<Employee> findAll();
    void update(Employee employee);
    void deleteById(int id);
}
