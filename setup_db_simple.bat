@echo off
cd /d "%~dp0"
set MYSQL_PATH=C:\Program Files\MySQL\MySQL Server 8.0\bin
set PASSWORD=#1#Mysql#1#

echo Creating database...
"%MYSQL_PATH%\mysql.exe" -u root -p%PASSWORD% -e "CREATE DATABASE IF NOT EXISTS emp_management;"

echo.
echo Creating tables and sample data...
"%MYSQL_PATH%\mysql.exe" -u root -p%PASSWORD% emp_management < database\schema.sql

echo.
echo ==========================================
echo Database setup complete!
echo ==========================================
pause
