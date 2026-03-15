package gui;

import dao.EmployeeDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Admin Dashboard - Main menu for the application
 * Provides navigation to all features
 */
public class AdminDashboard extends JFrame {
    
    private String adminUsername;
    private EmployeeDAO employeeDAO;
    
    public AdminDashboard(String username) {
        this.adminUsername = username;
        this.employeeDAO = new EmployeeDAO();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Employee Management System - Admin Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel - Dark theme
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        
        // Header panel - Dark blue
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(45, 45, 60));
        headerPanel.setPreferredSize(new Dimension(700, 100));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel lblTitle = new JLabel("ADMIN DASHBOARD", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        
        JLabel lblWelcome = new JLabel("Welcome, " + adminUsername + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblWelcome.setForeground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(45, 45, 60));
        titlePanel.add(lblTitle);
        titlePanel.add(lblWelcome);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        // Stats panel - Dark theme
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        statsPanel.setBackground(new Color(30, 30, 30));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(25, 10, 20, 10));
        
        int empCount = employeeDAO.getEmployeeCount();
        
        JLabel lblStats = new JLabel("Total Employees: " + empCount);
        lblStats.setFont(new Font("Arial", Font.BOLD, 18));
        lblStats.setForeground(new Color(100, 200, 255));
        statsPanel.add(lblStats);
        
        // Buttons panel - Dark theme
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 25, 20));
        buttonsPanel.setBackground(new Color(30, 30, 30));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));
        
        // Create buttons
        JButton btnAdd = createMenuButton("Add Employee", new Color(34, 139, 34));
        JButton btnView = createMenuButton("View Employees", new Color(70, 130, 180));
        JButton btnSearch = createMenuButton("Search Employee", new Color(255, 140, 0));
        JButton btnUpdate = createMenuButton("Update Employee", new Color(138, 43, 226));
        JButton btnDelete = createMenuButton("Delete Employee", new Color(220, 20, 60));
        JButton btnLogout = createMenuButton("Logout", new Color(128, 128, 128));
        
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnView);
        buttonsPanel.add(btnSearch);
        buttonsPanel.add(btnUpdate);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnLogout);
        
        // Footer panel - Dark theme
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(30, 30, 30));
        JLabel lblFooter = new JLabel("Employee Management System v1.0");
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        lblFooter.setForeground(new Color(150, 150, 150));
        footerPanel.add(lblFooter);
        
        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployee().setVisible(true);
            }
        });
        
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployees().setVisible(true);
            }
        });
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchEmployee().setVisible(true);
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empIdStr = JOptionPane.showInputDialog(AdminDashboard.this, 
                    "Enter Employee ID to Update:");
                if (empIdStr != null && !empIdStr.trim().isEmpty()) {
                    try {
                        int empId = Integer.parseInt(empIdStr.trim());
                        new UpdateEmployee(empId).setVisible(true);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AdminDashboard.this, 
                            "Invalid Employee ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
        
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        
        // Assemble
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        
        // Create center container - Dark theme
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(new Color(30, 30, 30));
        centerContainer.add(statsPanel, BorderLayout.NORTH);
        centerContainer.add(buttonsPanel, BorderLayout.CENTER);
        
        mainPanel.add(centerContainer, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JButton createMenuButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 60));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
        
        // Force proper painting on Metal L&F
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        return btn;
    }
    
    private void deleteEmployee() {
        String empIdStr = JOptionPane.showInputDialog(this, "Enter Employee ID to Delete:");
        if (empIdStr != null && !empIdStr.trim().isEmpty()) {
            try {
                int empId = Integer.parseInt(empIdStr.trim());
                
                // Confirm deletion
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete employee ID " + empId + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (employeeDAO.deleteEmployee(empId)) {
                        JOptionPane.showMessageDialog(this, 
                            "Employee deleted successfully!", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "Employee not found or could not be deleted!", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid Employee ID!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?",
            "Confirm Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            new AdminLogin().setVisible(true);
            this.dispose();
        }
    }
}
