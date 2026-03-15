package model;

/**
 * Employee Model Class
 * Represents an employee entity with all attributes
 * Demonstrates encapsulation with private fields and public getters/setters
 */
public class Employee {
    private int empId;
    private String name;
    private String department;
    private String position;
    private double salary;
    private String phone;
    private String email;
    private String joiningDate;
    
    // Default constructor
    public Employee() {
    }
    
    // Parameterized constructor
    public Employee(int empId, String name, String department, String position, 
                    double salary, String phone, String email, String joiningDate) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.joiningDate = joiningDate;
    }
    
    // Constructor without empId (for new employees)
    public Employee(String name, String department, String position, 
                    double salary, String phone, String email, String joiningDate) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.joiningDate = joiningDate;
    }
    
    // Getters and Setters
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getJoiningDate() {
        return joiningDate;
    }
    
    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                '}';
    }
}
