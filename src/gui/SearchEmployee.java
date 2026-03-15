package gui;

import model.Employee;
import dao.EmployeeDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Search Employee Form
 * Allows searching employees by ID or Name
 */
public class SearchEmployee extends JFrame {
    
    private JTextField txtSearch;
    private JComboBox<String> cmbSearchBy;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JButton btnSearch, btnClear, btnClose;
    
    private EmployeeDAO employeeDAO;
    
    public SearchEmployee() {
        employeeDAO = new EmployeeDAO();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Search Employee");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel - Dark theme
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        
        // Header - Dark orange
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(80, 60, 40));
        headerPanel.setPreferredSize(new Dimension(900, 60));
        
        JLabel lblTitle = new JLabel("SEARCH EMPLOYEE");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Search panel - Dark theme
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        searchPanel.setBackground(new Color(40, 40, 50));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        JLabel lblSearchBy = new JLabel("Search By:");
        lblSearchBy.setFont(new Font("Arial", Font.BOLD, 14));
        lblSearchBy.setForeground(Color.WHITE);
        searchPanel.add(lblSearchBy);
        
        cmbSearchBy = new JComboBox<>(new String[]{"Name", "Employee ID"});
        cmbSearchBy.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbSearchBy.setPreferredSize(new Dimension(120, 32));
        cmbSearchBy.setBackground(new Color(60, 60, 70));
        cmbSearchBy.setForeground(Color.WHITE);
        searchPanel.add(cmbSearchBy);
        
        txtSearch = new JTextField(25);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSearch.setPreferredSize(new Dimension(250, 35));
        txtSearch.setBackground(new Color(80, 80, 90));
        txtSearch.setForeground(Color.WHITE);
        txtSearch.setCaretColor(Color.WHITE);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 120, 130), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        searchPanel.add(txtSearch);
        
        btnSearch = createButton("Search", new Color(200, 140, 50));
        btnClear = createButton("Clear", new Color(100, 100, 100));
        
        searchPanel.add(btnSearch);
        searchPanel.add(btnClear);
        
        // Table
        String[] columns = {"Emp ID", "Name", "Department", "Position", "Salary", "Phone", "Email", "Joining Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("Arial", Font.PLAIN, 12));
        employeeTable.setRowHeight(28);
        employeeTable.setBackground(new Color(50, 50, 60));
        employeeTable.setForeground(Color.WHITE);
        employeeTable.setGridColor(new Color(80, 80, 90));
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        employeeTable.getTableHeader().setBackground(new Color(200, 140, 50));
        employeeTable.getTableHeader().setForeground(Color.WHITE);
        employeeTable.setSelectionBackground(new Color(200, 140, 50));
        employeeTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70), 1));
        scrollPane.getViewport().setBackground(new Color(50, 50, 60));
        
        // Button panel - Dark theme
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(30, 30, 30));
        
        btnClose = createButton("Close", new Color(70, 130, 180));
        buttonPanel.add(btnClose);
        
        // Add action listeners
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                tableModel.setRowCount(0);
                txtSearch.requestFocus();
            }
        });
        
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Enter key listener
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        
        // Assemble
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 40));
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
    
    private void performSearch() {
        String searchTerm = txtSearch.getText().trim();
        
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please enter search term!", 
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Clear previous results
        tableModel.setRowCount(0);
        
        String searchBy = (String) cmbSearchBy.getSelectedItem();
        List<Employee> results;
        
        if (searchBy.equals("Employee ID")) {
            // Search by ID
            try {
                int empId = Integer.parseInt(searchTerm);
                Employee emp = employeeDAO.getEmployeeById(empId);
                if (emp != null) {
                    addEmployeeToTable(emp);
                    setTitle("Search Employee - Found: 1");
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No employee found with ID: " + empId, 
                        "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    setTitle("Search Employee - Found: 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid Employee ID! Please enter a number.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Search by Name
            results = employeeDAO.searchEmployeesByName(searchTerm);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No employees found with name containing: " + searchTerm, 
                    "Not Found", JOptionPane.INFORMATION_MESSAGE);
                setTitle("Search Employee - Found: 0");
            } else {
                for (Employee emp : results) {
                    addEmployeeToTable(emp);
                }
                setTitle("Search Employee - Found: " + results.size());
            }
        }
    }
    
    private void addEmployeeToTable(Employee emp) {
        Object[] row = {
            emp.getEmpId(),
            emp.getName(),
            emp.getDepartment(),
            emp.getPosition(),
            String.format("%.2f", emp.getSalary()),
            emp.getPhone(),
            emp.getEmail(),
            emp.getJoiningDate()
        };
        tableModel.addRow(row);
    }
}
