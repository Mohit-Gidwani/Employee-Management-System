import gui.AdminLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Main Class - Entry point for Employee Management System
 * Initializes the application with login screen
 */
public class Main {
    
    public static void main(String[] args) {
        // Set look and feel to Metal for consistent dark theme rendering
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            // Customize Metal theme colors for dark mode
            UIManager.put("Panel.background", new java.awt.Color(30, 30, 30));
            UIManager.put("OptionPane.background", new java.awt.Color(30, 30, 30));
            UIManager.put("TextField.background", new java.awt.Color(80, 80, 90));
            UIManager.put("TextField.foreground", java.awt.Color.WHITE);
            UIManager.put("TextField.caretForeground", java.awt.Color.WHITE);
            UIManager.put("PasswordField.background", new java.awt.Color(80, 80, 90));
            UIManager.put("PasswordField.foreground", java.awt.Color.WHITE);
            UIManager.put("PasswordField.caretForeground", java.awt.Color.WHITE);
            UIManager.put("ComboBox.background", new java.awt.Color(80, 80, 90));
            UIManager.put("ComboBox.foreground", java.awt.Color.WHITE);
            UIManager.put("Table.background", new java.awt.Color(50, 50, 60));
            UIManager.put("Table.foreground", java.awt.Color.WHITE);
            UIManager.put("Table.gridColor", new java.awt.Color(80, 80, 90));
            UIManager.put("Table.selectionBackground", new java.awt.Color(70, 130, 180));
            UIManager.put("Table.selectionForeground", java.awt.Color.WHITE);
            UIManager.put("TableHeader.background", new java.awt.Color(70, 130, 180));
            UIManager.put("TableHeader.foreground", java.awt.Color.WHITE);
        } catch (Exception e) {
            System.out.println("Error setting look and feel: " + e.getMessage());
        }
        
        // Launch application on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminLogin loginForm = new AdminLogin();
                loginForm.setVisible(true);
            }
        });
    }
}
