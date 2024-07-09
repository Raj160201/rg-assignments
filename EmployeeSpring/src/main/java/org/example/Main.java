package org.example;

import org.example.config.AppConfig;
import org.example.model.Employee;
import org.example.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get EmployeeService bean
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        // Create and save new employees
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("John Doe");
        employee1.setDepartment("Engineering");
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Jane Smith");
        employee2.setDepartment("HR");
        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setName("Michael Johnson");
        employee3.setDepartment("Finance");

        employeeService.save(employee1);
        employeeService.save(employee2);
        employeeService.save(employee3);

        // Print all employees
        System.out.println("All Employees:");
        List<Employee> allEmployees = employeeService.findAll();
        for (Employee employee : allEmployees) {
            System.out.println(employee.getName() + " - " + employee.getDepartment());
        }

        // Update an employee
        Employee foundEmployee = employeeService.findById(1);
        if (foundEmployee != null) {
            foundEmployee.setDepartment("Marketing");
            employeeService.update(foundEmployee);
            System.out.println("Updated Employee: " + foundEmployee.getName() + " - " + foundEmployee.getDepartment());
        } else {
            System.out.println("Employee with ID 1 not found.");
        }

        // Print all employees after update
        System.out.println("\nAll Employees after update:");
        allEmployees = employeeService.findAll();
        for (Employee employee : allEmployees) {
            System.out.println(employee.getName() + " - " + employee.getDepartment());
        }

        // Delete an employee (employee2)
        employeeService.deleteById(2);
        System.out.println("\nEmployee with ID 2 deleted.");

        // Print all employees after deletion
        System.out.println("\nAll Employees after deletion:");
        allEmployees = employeeService.findAll();
        for (Employee employee : allEmployees) {
            System.out.println(employee.getName() + " - " + employee.getDepartment());
        }

        // Close the Spring context
        context.close();
    }
}
