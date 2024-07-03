package CoreJava.q6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeCRUD {

    private List<Employee> employeeList = new ArrayList<>();

    // Create: Add a new employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Added: " + employee);
    }

    // Read: Get an employee by ID
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        return employee.orElse(null);
    }

    // Update: Update employee details by ID
    public boolean updateEmployee(int id, String newName, String newDepartment) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(newName);
            employee.setDepartment(newDepartment);
            System.out.println("Updated: " + employee);
            return true;
        }
        System.out.println("Employee not found for ID: " + id);
        return false;
    }

    // Delete: Remove an employee by ID
    public boolean deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employeeList.remove(employee);
            System.out.println("Deleted: " + employee);
            return true;
        }
        System.out.println("Employee not found for ID: " + id);
        return false;
    }

    // Display all employees
    public void displayAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            employeeList.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();

        // Create employees
        Employee emp1 = new Employee(1, "Alice", "HR");
        Employee emp2 = new Employee(2, "Bob", "IT");
        Employee emp3 = new Employee(3, "Charlie", "Finance");

        crud.addEmployee(emp1);
        crud.addEmployee(emp2);
        crud.addEmployee(emp3);

        // Read employee
        System.out.println("Get Employee by ID 2: " + crud.getEmployeeById(2));

        // Update employee
        crud.updateEmployee(2, "Bob Smith", "Engineering");

        // Display all employees
        System.out.println("All Employees:");
        crud.displayAllEmployees();

        // Delete employee
        crud.deleteEmployee(2);

        // Display all employees after deletion
        System.out.println("All Employees after deletion:");
        crud.displayAllEmployees();
    }
}
