# Employee Management Application

This Java application allows for CRUD (Create, Read, Update, Delete) operations on an employee database using Swing for the UI and MySQL for the database.

## Features

- **Create Record:** Add a new employee to the database.
- **Read Records:** View employee information based on the entered name.
- **Update Record:** Update an employee's email address by their ID.
- **Delete Record:** Delete an employee from the database by their ID.

## Prerequisites

- Java Development Kit (JDK) installed
- MySQL server installed and running

## Usage

1. Clone this repository:
   ```bash
   git clone [repository_url](https://github.com/arsjadoun25/CRUD.git)
   
2. Open the project in your preferred Java IDE.

3. Update the database connection details in `Employeelist.java`:

    ```java
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "YOUR_PASSWORD";
    ```

4. Build and run the application.

5. Use the application to perform CRUD operations on the employee database.

## Database Structure

The application assumes a MySQL database with the following table structure:

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
```

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. **Fork the repository:**
   Click on the "Fork" button at the top right of this repository's page.

2. **Clone your fork:**
   ```bash
   git clone https://github.com/your-username/employee-management-app.git
   git checkout -b feature/my-feature
   MAKE CHANGES
   git push origin feature/my-feature
   ```
3. **Open a pull request:**
   Open a pull request on this repository and provide a detailed description of your changes.

We appreciate your contributions!



