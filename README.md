# Java OOP Library Console App

A comprehensive library management system built with Java. This project demonstrates core Object-Oriented Programming (OOP) principles and serves as a console-based application for managing books, authors, and library members.

## Technical Objectives

This project is designed to strictly adhere to Object-Oriented Design patterns and best practices:
- **Encapsulation:** Proper data hiding and access modifiers across all classes.
- **Composition & Inheritance:** Establishing clear, hierarchical relationships between entities.
- **Abstraction & Polymorphism:** Utilizing abstract classes and interfaces to create flexible, reusable methods.
- **Data Structures:** Implementing Java Collections (`List`, `Set`, `Map`) to efficiently manage and store data.

## Features

The library system supports the following core functionalities through its console interface:
- **Book Management:** Add, update, select (by ID, name, or author), and remove books from the system.
- **Advanced Listing:** Filter and list books by specific categories or authors.
- **Borrowing System:** 
  - Members can borrow available books.
  - The system tracks which member holds which book.
  - Implements a borrowing limit (maximum of 5 books per user).
- **Billing Integration:** Generates an invoice when a book is borrowed and processes refunds upon return.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code.

### Installation & Execution
1. Clone this repository to your local machine.
2. Open the project in your IDE.
3. Locate the `Main.java` file inside the `src/com/workintech/library/ui` package.
4. Run the `Main` class to start the interactive console interface.

## Project Structure

The codebase is organized into meaningful packages to maintain a clean architecture:
- `exceptions`: Custom error handling classes (`BookLimitExceededException`, etc.).
- `models`: Core domain entities representing library objects (`Book`, `Author`, `Reader`, etc.).
- `services`: Business logic and core operations (`Library`, `Librarian`, `Biller`).
- `ui`: Console entry point and data initialization classes.
