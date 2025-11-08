# Bank Management System  
[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)]()
[![Swing](https://img.shields.io/badge/Swing-UI-blue?style=flat)]()
[![License](https://img.shields.io/badge/License-MIT-green.svg)]()
[![Build](https://img.shields.io/badge/Build-Maven-orange?style=flat)]()

A Java Swing–based desktop application that simulates a real-world banking system with persistent storage, secure transactions, and modular design following **MVC architecture** and **SOLID principles**.

---

## Features
- Create, update, and delete customer accounts  
- Deposit, withdraw, and transfer funds securely  
- Persistent storage using **H2 Database (JDBC)**  
- File-based transaction logging  
- Modular **MVC structure** for maintainability and scalability  
- Clean Java Swing GUI for intuitive user interaction  

---

## Tech Stack

| Layer | Technology |
|:------|:------------|
| Language | Java |
| GUI | Swing |
| Database | H2 (JDBC) |
| Design | MVC Architecture |
| Build Tool | Maven |
| Logging | File-based persistence |

---



---

## Setup & Run

1. Clone the repository

   git clone https://github.com/bhavyabafnaa/BankManagementSystem.git

   cd BankManagementSystem

3. Build the project

   mvn clean install

4. Run the application

   mvn exec:java -Dexec.mainClass="com.bank.ui.Main"


The Java Swing interface will launch automatically.


Design Highlights
Modular MVC layout separating UI, logic, and data layers
Implements SOLID and object-oriented best practices
Maintains real-time transaction logs with persistent H2 storage

Documentation & Screenshots
For screenshots, workflow explanation, and detailed system design,
refer to the report.pdf included in this repository.


Future Enhancements
User authentication and encryption
REST API layer for remote access
Analytics dashboard with charts
Migration to PostgreSQL or cloud databases

Author
Bhavya Bafna
📧 bhavyabafna.in@gmail.com
🔗 GitHub • LinkedIn
If you found this project useful, consider starring the repository.

---

This version is correctly structured for GitHub rendering — headers, lists, and code blocks are properly nested, and it cleanly points readers to your `report.pdf` for visuals and deeper details.
