# Finance Dashboard Backend

## Overview

Finance Dashboard Backend is a Spring Boot REST API for managing financial records such as income and expenses.
It provides secure authentication using JWT and supports role-based access control.

This project demonstrates backend architecture used in real-world applications.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* MySQL
* Hibernate / JPA
* Maven
* Swagger (OpenAPI)
* GitHub
* Railway Deployment

---

## Features

### Authentication

* User Registration
* Login with JWT Token
* Secure API endpoints

### Role Based Access Control

Roles supported:

* ADMIN
* ANALYST
* VIEWER

Permissions are enforced using Spring Security.

### Financial Records

Users can:

* Create financial records
* View records
* Delete records
* Filter records
* Paginated record listing

### Dashboard Analytics

Provides financial insights:

* Total Income
* Total Expense
* Net Balance
* Category summaries
* Monthly trends

---

## API Documentation

Swagger UI:

```
/swagger-ui/index.html
```

Example (local):

```
http://localhost:8080/swagger-ui/index.html
```

---

## Example API Endpoints

Authentication

```
POST /auth/register
POST /auth/login
```

Financial Records

```
POST /records
GET /records?page=0&size=10
DELETE /records/{id}
```

Dashboard

```
GET /dashboard/summary
```

---

## Running the Project Locally

Clone the repository:

```
git clone https://github.com/KunalPatil94/finance-dashboard-backend.git
```

Navigate to project:

```
cd finance-dashboard-backend
```

Run application:

```
mvn spring-boot:run
```

---

## Deployment

The application is deployed using **Railway**.

Production URL:

```
(Add deployment URL here after deployment)
```

---

## Project Architecture

```
Controller → Service → Repository → Database
            ↓
         Security (JWT)
```

Layers:

* Controller
* Service
* Repository
* DTO
* Security
* Config
* Exception Handling

---

## Author

Kunal R. Patil
