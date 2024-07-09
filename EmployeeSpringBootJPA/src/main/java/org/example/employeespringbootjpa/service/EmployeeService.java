package org.example.employeespringbootjpa.service;

import org.example.employeespringbootjpa.entity.Employee;
import org.example.employeespringbootjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        System.out.println("Creating employee: " + employee);
        Employee createdEmployee = employeeRepository.save(employee);
        System.out.println("Employee created: " + createdEmployee);
        return createdEmployee;
    }

    public Optional<Employee> getEmployeeById(int id) {
        System.out.println("Fetching employee with ID: " + id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            System.out.println("Employee found: " + employee.get());
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
        return employee;
    }

    public List<Employee> getAllEmployees() {
        System.out.println("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("All employees: " + employees);
        return employees;
    }

    public Employee updateEmployee(Employee employee) {
        System.out.println("Updating employee: " + employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        System.out.println("Employee updated: " + updatedEmployee);
        return updatedEmployee;
    }

    public void deleteEmployee(int id) {
        System.out.println("Deleting employee with ID: " + id);
        employeeRepository.deleteById(id);
        System.out.println("Employee with ID " + id + " deleted.");
    }
}
