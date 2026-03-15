# Employee Management System

[![Java](https://img.shields.io/badge/Java-8%2B-blue)](https://www.java.com)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)](https://www.mysql.com)
[![License](https://img.shields.io/badge/License-Educational-green)](LICENSE)

A complete Java desktop application for managing employee records with MySQL database. Built with Java Swing GUI and demonstrates core OOP concepts.

## 🎯 Project Overview

This Employee Management System is a college-level project that provides a complete CRUD (Create, Read, Update, Delete) application for managing employee data. It features a dark-themed modern UI and secure admin authentication.

**Key Highlights:**
- 🔐 Secure admin login system
- 👥 Complete employee management (Add, View, Search, Update, Delete)
- 🎨 Modern dark-themed UI
- 💾 MySQL database integration
- 📊 Sample data included

## 🚀 Features

| Feature | Description |
|---------|-------------|
| **Admin Login** | Secure authentication with username/password |
| **Add Employee** | Create new employee records with full details |
| **View Employees** | Display all employees in a styled table format |
| **Search Employee** | Quick search by Employee ID or Name |
| **Update Employee** | Modify existing employee information |
| **Delete Employee** | Remove employee records with confirmation |
| **Dashboard** | Menu-driven interface with employee statistics |

## 🛠️ Technology Stack

- **Language**: Core Java (JDK 8+)
- **GUI Framework**: Java Swing (Dark Theme)
- **Database**: MySQL 8.0
- **Connectivity**: JDBC (MySQL Connector/J)
- **Architecture**: DAO Pattern (Data Access Object)

## 📁 Project Structure

```
emp-management/
├── src/
│   ├── Main.java                 # Application entry point
│   ├── model/
│   │   └── Employee.java         # Employee entity (POJO)
│   ├── dao/
│   │   ├── DBConnection.java     # Database connection (config-based)
│   │   ├── EmployeeDAO.java      # Employee CRUD operations
│   │   └── AdminDAO.java         # Admin authentication
│   └── gui/
│       ├── AdminLogin.java       # Login screen with dark theme
│       ├── AdminDashboard.java   # Main dashboard menu
│       ├── AddEmployee.java      # Add employee form
│       ├── ViewEmployees.java    # Employee list view
│       ├── UpdateEmployee.java   # Update employee form
│       └── SearchEmployee.java   # Search functionality
├── database/
│   └── schema.sql                # MySQL database schema
├── docs/
│   └── documentation.md          # Detailed documentation
├── config.properties.template    # Configuration template
├── setup_database.bat            # Database setup script
├── run_project.bat               # Quick run script
└── README.md                     # This file
```

## 📋 Prerequisites

Before running this project, ensure you have:

1. **Java JDK** 8 or higher installed
2. **MySQL Server** 5.7 or higher installed
3. **MySQL JDBC Driver** (Connector/J)

## 🔧 Setup Instructions

### Step 1: Install MySQL

Download and install MySQL Server from [https://dev.mysql.com/downloads/installer/]

Remember your MySQL root password during installation.

### Step 2: Configure Database Connection

1. Copy the configuration template:
   ```bash
   copy config.properties.template config.properties
   ```

2. Edit `config.properties` with your MySQL credentials:
   ```properties
   db.url=jdbc:mysql://localhost:3306/emp_management
   db.user=root
   db.password=YOUR_MYSQL_PASSWORD
   ```

   ⚠️ **Important**: Never commit `config.properties` to GitHub (it's already in `.gitignore`)

### Step 3: Create Database

Run the database setup script:

```bash
setup_database.bat
```

Or manually run the SQL file:
```bash
mysql -u root -p < database/schema.sql
```

### Step 4: Download MySQL JDBC Driver

1. Download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/
2. Extract to `lib/mysql-connector-j-8.0.33.jar`

### Step 5: Run the Application

#### Option A: Quick Run (Windows)
```bash
run_project.bat
```

#### Option B: Manual Compile & Run
```bash
# Compile
javac -d classes -cp "lib/mysql-connector-j-8.0.33.jar" src/model/*.java src/dao/*.java src/gui/*.java src/Main.java

# Run
java -cp "classes;lib/mysql-connector-j-8.0.33.jar" Main
```

## 🔑 Default Login Credentials

- **Username**: `admin`
- **Password**: `admin123`

## 📖 How to Use

1. **Login**: Enter admin credentials on the login screen
2. **Dashboard**: View employee statistics and select operations:
   - **Add Employee** - Create new employee record
   - **View Employees** - Browse all employees in table
   - **Search Employee** - Find by ID or Name
   - **Update Employee** - Edit employee details (enter ID first)
   - **Delete Employee** - Remove employee (enter ID with confirmation)
   - **Logout** - Return to login screen

## 🗄️ Database Schema

### Admin Table
```sql
CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);
```

### Employee Table
```sql
CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    joining_date DATE NOT NULL
);
```

## 🎨 UI Screenshots

The application features a modern dark theme with:
- Dark background (#303030)
- White text for high contrast
- Colored headers matching each form's purpose
- Styled input fields and buttons
- Responsive table views

## 🐛 Troubleshooting

| Error | Solution |
|-------|----------|
| `config.properties not found` | Create file from template and add your password |
| `MySQL JDBC Driver not found` | Ensure JAR is in `lib/` folder and classpath is correct |
| `Access denied for user` | Check username/password in config.properties |
| `Unknown database` | Run setup_database.bat to create database |
| `ClassNotFoundException` | Verify MySQL Connector JAR is present |

## 🎓 OOP Concepts Demonstrated

1. **Encapsulation**: Private fields with public getters/setters in Employee class
2. **Abstraction**: DAO classes hide database implementation details
3. **Inheritance**: GUI classes extend JFrame and other Swing components
4. **Polymorphism**: Method overloading and interface implementations
5. **Classes & Objects**: Employee entity, DAO objects, GUI forms

## 🔒 Security Notes

- Passwords are stored in `config.properties` (excluded from Git via `.gitignore`)
- Never commit actual configuration files to public repositories
- Use strong MySQL root passwords in production
- Consider implementing password hashing for production use

## 📄 License

This project is for **educational purposes only**.

## 👤 Author

College Student Project - Employee Management System

---

**Need Help?** Check the `docs/documentation.md` file for detailed project documentation including system requirements, working principles, and future enhancements.
