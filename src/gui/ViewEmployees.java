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
 * View Employees Form
 * Displays all employees in a table format
 */
public class ViewEmployees extends JFrame {
    
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JButton btnRefresh, btnClose;
    private EmployeeDAO employeeDAO;
    
    public ViewEmployees() {
        employeeDAO = new EmployeeDAO();
        initializeUI();
        loadEmployees();
    }
    
    private void initializeUI() {
        setTitle("View All Employees");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel - Dark theme
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        
        // Header - Dark blue
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(45, 45, 60));
        headerPanel.setPreferredSize(new Dimension(900, 60));
        
        JLabel lblTitle = new JLabel("EMPLOYEE LIST");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Table
        String[] columns = {"Emp ID", "Name", "Department", "Position", "Salary", "Phone", "Email", "Joining Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        employeeTable = new JTable(tableModel);
        employeeTable.setFont(new Font("Arial", Font.PLAIN, 12));
        employeeTable.setRowHeight(28);
        employeeTable.setBackground(new Color(50, 50, 60));
        employeeTable.setForeground(Color.WHITE);
        employeeTable.setGridColor(new Color(80, 80, 90));
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        employeeTable.getTableHeader().setBackground(new Color(70, 130, 180));
        employeeTable.getTableHeader().setForeground(Color.WHITE);
        employeeTable.setSelectionBackground(new Color(70, 130, 180));
        employeeTable.setSelectionForeground(Color.WHITE);
        
        // Scroll pane - Dark theme
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70), 1));
        scrollPane.getViewport().setBackground(new Color(50, 50, 60));
        
        // Button panel - Dark theme
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(30, 30, 30));
        
        btnRefresh = createButton("Refresh", new Color(34, 139, 34));
        btnClose = createButton("Close", new Color(100, 100, 100));
        
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnClose);
        
        // Add action listeners
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEmployees();
            }
        });
        
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        // Assemble
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
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
    
    private void loadEmployees() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Load from database
        List<Employee> employees = employeeDAO.getAllEmployees();
        
        // Add to table
        for (Employee emp : employees) {
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
        
        // Update title with count
        setTitle("View All Employees - Total: " + employees.size());
    }
}
