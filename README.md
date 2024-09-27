---

# Employee Management System

## Project Overview

The **Employee Management System (EMS)** is a comprehensive web application designed using **Java Full Stack Development** practices. The system allows users to manage employee and department information in a corporate setting. This project follows a three-tier architecture, which includes the presentation layer (frontend), business logic (backend), and database layer.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Frontend Details](#frontend-details)
- [Backend Details](#backend-details)
- [Contributing](#contributing)

## Features

- **Employee Management**: Add, update, view, and delete employee details.
- **Department Management**: Manage departments including adding, updating, viewing, and deleting departments.
- **RESTful API**: Expose CRUD operations via RESTful endpoints for employees and departments.
- **Responsive Frontend**: The user interface is developed using HTML, CSS, Bootstrap, and JavaScript.
- **Backend with Business Logic**: Java, JAX-RS (Jersey), and Hibernate are used for backend development, with MySQL for data storage.
  
## Technologies Used

### Frontend:
- **HTML**
- **CSS**
- **Bootstrap**
- **JavaScript**

### Backend:
- **Java**: Core application logic.
- **JAX-RS (Jersey)**: For building RESTful APIs.
- **JPA/Hibernate**: For ORM and database interaction.
- **MySQL**: Relational database for persistent storage.
- **Maven**: Project and dependency management.

### DevOps:
- **Git**: Version control.
- **GitHub**: Repository hosting.

## Project Structure

```bash
src
└── main
    ├── java
    │   └── com
    │       └── deloitte
    │           ├── demo
    │           │   ├── model             # Entity classes
    │           │   │   ├── Employee.java
    │           │   │   └── Department.java
    │           │   ├── repository        # Data access layer
    │           │   │   ├── EmployeeRepository.java
    │           │   │   └── DepartmentRepository.java
    │           │   ├── service           # Business logic layer
    │           │   │   ├── EmployeeService.java
    │           │   │   └── DepartmentService.java
    │           │   └── resource          # REST API layer
    │           │       ├── EmployeeResource.java
    │           │       └── DepartmentResource.java
    │           └── config                # Database config
    │               └── PersistenceConfig.java
    └── resources
        └── META-INF
            └── persistence.xml           # JPA and Hibernate configurations
```

## Installation & Setup

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/MahanthiSriAsish1/Deloitte-Group-Training.git
   cd Deloitte-Group-Training
   ```

2. **Set Up MySQL Database**:
   - Create a database named `ems_db`.
   - Import the SQL file from the `/resources/` folder to set up the schema.

3. **Configure Database Connection**:
   - Update `persistence.xml` in `/resources/META-INF/` to point to your MySQL instance.

4. **Build the Project**:
   - Use Maven to build the project:

     ```bash
     mvn clean install
     ```

5. **Run the Application**:
   - Deploy the project on a Tomcat server (or any other servlet container).
   - Access the application at: `http://localhost:8090/employee-management-system/`.

## API Endpoints

### Employee Management:
- **GET** `/employees` - Retrieve all employees.
- **GET** `/employees/{id}` - Get employee by ID.
- **POST** `/employees` - Add a new employee.
- **PUT** `/employees/{id}` - Update employee details.
- **DELETE** `/employees/{id}` - Delete employee.

### Department Management:
- **GET** `/departments` - Retrieve all departments.
- **GET** `/departments/{id}` - Get department by ID.
- **POST** `/departments` - Add a new department.
- **PUT** `/departments/{id}` - Update department details.
- **DELETE** `/departments/{id}` - Delete department.

## Frontend Details

The frontend is developed using **HTML, CSS, Bootstrap**, and **JavaScript** for building a responsive and interactive UI. All CRUD operations for employees and departments are accessible via the user interface.

### Frontend Features:
- Responsive design using Bootstrap.
- JavaScript for client-side form validation.
- Interactive UI for managing employee and department data.

## Backend Details

The backend is powered by **Java**, using **JAX-RS (Jersey)** for building RESTful services, and **JPA/Hibernate** for ORM. **MySQL** serves as the database for storing employee and department information.

### Key Backend Features:
- Separation of concerns using a three-tier architecture.
- Fully functional REST API for managing data.
- ORM-based database interaction using JPA and Hibernate.

---
