@echo off
title Ticket Booking System - Setup

echo.
echo ==========================================
echo       TICKET BOOKING SYSTEM
echo ==========================================
echo.

echo Checking Java installation...
java -version >nul 2>&1

if %errorlevel% neq 0 (
echo.
echo ERROR: Java is not installed.
echo.
echo Please install Java 22 and try again.
echo.
pause
exit /b 1
)

echo Java installation found.
echo.

echo Checking Java version...
for /f "tokens=3" %%v in ('java -version 2^>^&1 ^| findstr /i "version"') do (
echo Detected Java version: %%v
)

echo.
echo Building the project...
echo This may take a little while the first time.
echo.

call gradlew.bat build

if %errorlevel% neq 0 (
echo.
echo ==========================================
echo BUILD FAILED
echo ==========================================
echo.
echo Please check the error shown above.
echo.
pause
exit /b 1
)

echo.
echo ==========================================
echo BUILD SUCCESSFUL
echo ==========================================
echo.
echo Starting Ticket Booking System...
echo.

call gradlew.bat run --console=plain

echo.
echo ==========================================
echo Application closed.
echo ==========================================
echo.

pause
