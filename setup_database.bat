@echo off
cd /d "%~dp0"
echo ==========================================
echo Employee Management - Database Setup
echo ==========================================
echo.

REM Get MySQL path from config or use default
set MYSQL_PATH=C:\Program Files\MySQL\MySQL Server 8.0\bin

REM Prompt for MySQL password
set /p MYSQL_PWD=Enter MySQL root password: 

echo.
echo Creating database...
"%MYSQL_PATH%\mysql.exe" -u root -p%MYSQL_PWD% -e "CREATE DATABASE IF NOT EXISTS emp_management;"

echo.
echo Loading schema...
"%MYSQL_PATH%\mysql.exe" -u root -p%MYSQL_PWD% emp_management < database\schema.sql

echo.
echo ==========================================
echo Database setup complete!
echo ==========================================
pause
