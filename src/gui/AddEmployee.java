package gui;

import model.Employee;
import dao.EmployeeDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add Employee Form
 * Allows admin to add new employees to the system
 */
public class AddEmployee extends JFrame {
    
    private JTextField txtName, txtDepartment, txtPosition, txtSalary, txtPhone, txtEmail, txtJoiningDate;
    private JButton btnAdd, btnClear, btnBack;
    
    private EmployeeDAO employeeDAO;
    
    public AddEmployee() {
        employeeDAO = new EmployeeDAO();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Add New Employee");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel - Dark theme
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        
        // Header - Dark green
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(40, 80, 40));
        headerPanel.setPreferredSize(new Dimension(500, 70));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel lblTitle = new JLabel("ADD NEW EMPLOYEE");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Form panel - Dark theme
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(40, 40, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form fields
        txtName = createFormField(formPanel, gbc, 0, "Full Name:", "");
        txtDepartment = createFormField(formPanel, gbc, 1, "Department:", "");
        txtPosition = createFormField(formPanel, gbc, 2, "Position:", "");
        txtSalary = createFormField(formPanel, gbc, 3, "Salary:", "");
        txtPhone = createFormField(formPanel, gbc, 4, "Phone Number:", "");
        txtEmail = createFormField(formPanel, gbc, 5, "Email Address:", "");
        txtJoiningDate = createFormField(formPanel, gbc, 6, "Joining Date (YYYY-MM-DD):", "");
        
        // Buttons panel - Dark theme
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(40, 40, 50));
        
        btnAdd = createButton("Add Employee", new Color(34, 139, 34));
        btnClear = createButton("Clear", new Color(169, 169, 169));
        btnBack = createButton("Back", new Color(70, 130, 180));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBack);
        
        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Assemble
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JTextField createFormField(JPanel panel, GridBagConstraints gbc, int row, String label, String defaultValue) {
        // Label with white text
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lbl, gbc);
        
        // Text field with dark theme
        JTextField txt = new JTextField(25);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setText(defaultValue);
        txt.setBackground(new Color(80, 80, 90));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 120, 130), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txt.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txt, gbc);
        
        return txt;
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        return btn;
    }
    
    private void addEmployee() {
        // Get values
        String name = txtName.getText().trim();
        String department = txtDepartment.getText().trim();
        String position = txtPosition.getText().trim();
        String salaryStr = txtSalary.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String joiningDate = txtJoiningDate.getText().trim();
        
        // Validation
        if (name.isEmpty() || department.isEmpty() || position.isEmpty() || 
            salaryStr.isEmpty() || joiningDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill all required fields!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Parse salary
        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
            if (salary < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Salary cannot be negative!", 
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid salary amount!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Create employee object
        Employee emp = new Employee(name, department, position, salary, phone, email, joiningDate);
        
        // Save to database
        if (employeeDAO.addEmployee(emp)) {
            JOptionPane.showMessageDialog(this, 
                "Employee added successfully!\nEmployee ID: " + emp.getEmpId(), 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Failed to add employee!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        txtName.setText("");
        txtDepartment.setText("");
        txtPosition.setText("");
        txtSalary.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtJoiningDate.setText("");
        txtName.requestFocus();
    }
}
