                                                                    Invoice Management System
The Invoice Management System is a web-based application built using Spring Boot that allows users to manage invoices and customer details efficiently. This application provides functionalities for creating, viewing, updating, and deleting invoices, along with pagination support for browsing through a large number of invoices.

Features
.CRUD Operations: Create, read, update, and delete invoices.
.Pagination: View invoices with pagination support to handle large datasets.
.Form Validation: Validate form inputs to ensure data integrity.
.Exception Handling: Gracefully handle unexpected errors to provide a smooth user experience.
.Session Management: Manage session attributes to display messages and handle user interactions effectively.
.Database Integration: Utilize PostgreSQL as the runtime database for storing invoice and customer details.

Technologies Used:

Spring Boot: Backend framework for building Java-based applications.
Spring Data JPA: Simplifies database operations with the Java Persistence API.
Thymeleaf: Server-side Java template engine for HTML rendering.
Hibernate Validator: Perform validation of Java objects.
PostgreSQL: Open-source relational database management system.
HTML & CSS: Frontend technologies for building user interfaces.
Maven: Build automation tool for managing project dependencies and lifecycle.

Setup:
1.	Clone the repository: 
2.	Navigate the project Directory:
3.	Build and Run the application :
4.	Access the application : http://localhost:9092/invoices/

Usage
.Adding an Invoice: Click on the "Add Invoice" link and fill out the form with the necessary .details. Click the "Submit" button to add the invoice.
.Viewing Invoices: Navigate to the "All Invoices" page to view all invoices paginated. Use the pagination links to navigate between pages.
.Updating an Invoice: Click on the "Update" button next to an invoice to edit its details. Make the necessary changes and click the "Update" button to save the changes.
.Deleting an Invoice: Click on the "Delete" button next to an invoice to delete it permanently.
