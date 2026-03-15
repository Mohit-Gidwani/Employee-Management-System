package gui;

import dao.AdminDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Admin Login Form
 * Provides secure login interface for admin users
 */
public class AdminLogin extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnClear;
    private JToggleButton btnShowPassword;
    
    private AdminDAO adminDAO;
    
    public AdminLogin() {
        adminDAO = new AdminDAO();
        initializeUI();
    }
    
    private void initializeUI() {
        // Frame setup
        setTitle("Employee Management System - Admin Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel - Dark theme
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        
        // Header panel - Dark blue
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(45, 45, 60));
        headerPanel.setPreferredSize(new Dimension(400, 80));
        
        JLabel lblTitle = new JLabel("ADMIN LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Form panel - Dark theme
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(40, 40, 50));
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 20, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        
        // Username - White text
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblUsername, gbc);
        
        txtUsername = new JTextField(18);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBackground(new Color(80, 80, 90));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 120, 130), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        formPanel.add(txtUsername, gbc);
        
        // Password - White text
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        formPanel.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField(18);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBackground(new Color(80, 80, 90));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 120, 130), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        txtPassword.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(txtPassword, gbc);
        
        // Show/Hide password toggle button
        btnShowPassword = new JToggleButton("Show");
        btnShowPassword.setFont(new Font("Arial", Font.BOLD, 12));
        btnShowPassword.setBackground(new Color(60, 60, 70));
        btnShowPassword.setForeground(Color.WHITE);
        btnShowPassword.setFocusPainted(false);
        btnShowPassword.setPreferredSize(new Dimension(60, 32));
        btnShowPassword.setToolTipText("Show/Hide Password");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(btnShowPassword, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Buttons panel - Dark theme
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(40, 40, 50));
        
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(100, 40));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 110), 1));
        btnLogin.setOpaque(true);
        btnLogin.setContentAreaFilled(true);
        
        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBackground(new Color(100, 100, 100));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setPreferredSize(new Dimension(100, 40));
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClear.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 110), 1));
        btnClear.setOpaque(true);
        btnClear.setContentAreaFilled(true);
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClear);
        
        // Info panel with dark background
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(30, 30, 30));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        
        JLabel lblInfo = new JLabel("Default Login: admin / admin123");
        lblInfo.setFont(new Font("Arial", Font.BOLD, 13));
        lblInfo.setForeground(new Color(180, 180, 180));
        infoPanel.add(lblInfo);
        
        // Add action listeners
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        btnShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnShowPassword.isSelected()) {
                    txtPassword.setEchoChar((char) 0);
                    btnShowPassword.setText("Hide");
                } else {
                    txtPassword.setEchoChar('*');
                    btnShowPassword.setText("Show");
                }
            }
        });
        
        // Enter key listener for password field
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        
        // Assemble main panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(30, 30, 30));
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void performLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter both username and password!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Authenticate
        if (adminDAO.validateLogin(username, password)) {
            JOptionPane.showMessageDialog(this, 
                "Login Successful! Welcome " + username + "!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Open Admin Dashboard
            AdminDashboard dashboard = new AdminDashboard(username);
            dashboard.setVisible(true);
            
            // Close login window
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid username or password!", 
                "Login Failed", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }
    
    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
}
