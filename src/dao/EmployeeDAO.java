package dao;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee Data Access Object (DAO) Class
 * Handles all database operations for Employee entity
 * Demonstrates abstraction - hides database implementation details
 */
public class EmployeeDAO {
    
    /**
     * Add new employee to database
     * @param employee Employee object to add
     * @return true if successful, false otherwise
     */
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (name, department, position, salary, phone, email, joining_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getDepartment());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getPhone());
            pstmt.setString(6, employee.getEmail());
            pstmt.setString(7, employee.getJoiningDate());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Get the generated employee ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        employee.setEmpId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Get all employees from database
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee ORDER BY emp_id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee emp = extractEmployeeFromResultSet(rs);
                employees.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Error getting employees: " + e.getMessage());
        }
        return employees;
    }
    
    /**
     * Get employee by ID
     * @param empId Employee ID to search
     * @return Employee object if found, null otherwise
     */
    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, empId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractEmployeeFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting employee by ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Search employees by name
     * @param name Name to search (partial match supported)
     * @return List of matching employees
     */
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE name LIKE ? ORDER BY name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    employees.add(extractEmployeeFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching employees: " + e.getMessage());
        }
        return employees;
    }
    
    /**
     * Update employee details
     * @param employee Employee object with updated data
     * @return true if successful, false otherwise
     */
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = ?, department = ?, position = ?, " +
                     "salary = ?, phone = ?, email = ?, joining_date = ? WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getDepartment());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setString(5, employee.getPhone());
            pstmt.setString(6, employee.getEmail());
            pstmt.setString(7, employee.getJoiningDate());
            pstmt.setInt(8, employee.getEmpId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Delete employee by ID
     * @param empId Employee ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE emp_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, empId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Get total employee count
     * @return total number of employees
     */
    public int getEmployeeCount() {
        String sql = "SELECT COUNT(*) FROM employee";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error counting employees: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Helper method to extract employee data from ResultSet
     */
    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpId(rs.getInt("emp_id"));
        emp.setName(rs.getString("name"));
        emp.setDepartment(rs.getString("department"));
        emp.setPosition(rs.getString("position"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setPhone(rs.getString("phone"));
        emp.setEmail(rs.getString("email"));
        emp.setJoiningDate(rs.getString("joining_date"));
        return emp;
    }
}
