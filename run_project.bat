@echo off
echo ==========================================
echo Employee Management System - Run Project
echo ==========================================
echo.

:: Create directories
if not exist classes mkdir classes
if not exist lib mkdir lib

:: Check for MySQL JDBC Driver
if not exist "lib\mysql-connector-java-8.0.33.jar" (
    echo Downloading MySQL JDBC Driver...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar' -OutFile 'lib\mysql-connector-java-8.0.33.jar'"
    if errorlevel 1 (
        echo Failed to download JDBC driver automatically.
        echo Please download from: https://dev.mysql.com/downloads/connector/j/
        echo Place the JAR in the 'lib' folder.
        pause
        exit /b 1
    )
)

echo Compiling Java files...
javac -d classes -cp "lib\mysql-connector-java-8.0.33.jar" src\model\*.java src\dao\*.java src\gui\*.java src\Main.java

if errorlevel 1 (
    echo Compilation failed! Please check for errors.
    pause
    exit /b 1
)

echo.
echo Running Employee Management System...
echo ==========================================
java -cp "classes;lib\mysql-connector-java-8.0.33.jar" Main

if errorlevel 1 (
    echo.
    echo Application exited with error.
    echo Make sure MySQL is running and database is created.
    echo Run setup_database.bat first if needed.
)

pause
