package CoreJava.q7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CoreJava.q6.Employee;

public class EmployeeJDBC {

    private static final String URL = "jdbc:postgresql://localhost:5432/Employee";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1602";

    // Create a new employee
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO Employee (id, name, department) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getDepartment());
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " record(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read an employee by ID
    public Employee readEmployee(int id) {
        String query = "SELECT * FROM Employee WHERE id = ?";
        Employee employee = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int empId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                employee = new Employee(empId, name, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // Update an employee's details
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, department = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDepartment());
            preparedStatement.setInt(3, employee.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " record(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an employee by ID
    public void deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            System.out.println(result + " record(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test CRUD operations
    public static void main(String[] args) {
        EmployeeJDBC jdbc = new EmployeeJDBC();

        // Create a new employee
        Employee emp1 = new Employee(1, "Alice", "HR");
        jdbc.createEmployee(emp1);
        Employee emp2 = new Employee(2, "Bob", "IT");
        jdbc.createEmployee(emp2);
        Employee emp3 = new Employee(3, "Charlie", "Finance");
        jdbc.createEmployee(emp3);

        // Read an employee
        Employee retrievedEmp = jdbc.readEmployee(1);
        System.out.println("Retrieved: " + retrievedEmp);

        // Update employee
        Employee empToUpdate = new Employee(1, "Alice Smith", "Recruitment");
        jdbc.updateEmployee(empToUpdate);

        // Delete employee
        jdbc.deleteEmployee(1);
    }
}
