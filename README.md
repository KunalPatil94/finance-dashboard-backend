💰 Finance Dashboard Backend

A Spring Boot REST API for managing financial records (income and expenses) with JWT authentication, role-based access control, and financial analytics dashboard.

This project demonstrates backend architecture used in real-world enterprise applications including authentication, secure APIs, layered architecture, and cloud deployment.

🚀 Live API

API Base URL

https://resourceful-youthfulness-production-8c81.up.railway.app

*( Direct access to root may return 403 because authentication is required. )*

Swagger API Documentation 

https://resourceful-youthfulness-production-8c81.up.railway.app/swagger-ui/index.html

📄 Project Documentation

Detailed project documentation is available here:
👉 https://github.com/KunalPatil94/finance-dashboard-backend/blob/main/docs/Finance_Dashboard_Documentation.pdf


📌 Features

🔐 Authentication

Secure authentication using JWT (JSON Web Token).

Supported functionality:
User registration
User login
Secure JWT token generation
Stateless authentication
Protected API endpoints

Example header:
Authorization: Bearer <JWT_TOKEN>


👥 Role Based Access Control

Three roles are supported in the system.

<img width="780" height="144" alt="Screenshot 2026-04-05 232759" src="https://github.com/user-attachments/assets/62489ebd-6502-4190-9117-7f19fb530adc" />


Security rules are enforced using Spring Security annotations.

Example: @PreAuthorize("hasAnyAuthority('ADMIN','ANALYST')")



📊 Dashboard Analytics

The backend provides analytics endpoints that help users understand financial data.

Dashboard provides:

1.Total income
2.Total expenses
3.Net balance
4.Category summaries
5.Monthly trends
6.Recent transactions


🛠 Tech Stack

<img width="1031" height="548" alt="Screenshot 2026-04-05 233215" src="https://github.com/user-attachments/assets/6f2f2667-8ccf-43a9-8d2c-6c0e0133e318" />


🏗 System Architecture

<img width="530" height="1285" alt="mermaid-diagram" src="https://github.com/user-attachments/assets/e352e66b-be8f-4125-ac6a-b757a69547bf" />

The project follows a layered architecture pattern.

Layer Responsibilities

1.Controller Layer - Handles HTTP requests and API endpoints.

2.Service Layer - Contains business logic and processing.

3.Repository Layer - Handles database operations using JPA.

4.Database - Stores user and financial data.



📂 Project Structure

<img width="773" height="765" alt="Screenshot 2026-04-05 232715" src="https://github.com/user-attachments/assets/7c1aa92f-f5ef-436d-a6d6-741eef487d59" />


🗄 Database Schema

<img width="403" height="628" alt="Screenshot 2026-04-05 231345" src="https://github.com/user-attachments/assets/47987acf-498d-4f48-a848-743273e5e0fc" />


Entity Relationship

<img width="478" height="1669" alt="mermaid-diagram (1)" src="https://github.com/user-attachments/assets/c3efb967-03e6-4d4d-a66e-a04eec4e2baa" />



🔑 JWT Authentication Flow

<img width="2172" height="986" alt="mermaid-diagram (2)" src="https://github.com/user-attachments/assets/2662787e-d4cc-4145-b4fd-306933a99d3f" />



📡 API Endpoints

<img width="783" height="470" alt="Screenshot 2026-04-05 232156" src="https://github.com/user-attachments/assets/a2fb25ed-6d89-4a31-a54a-8c61ccbe54b0" />




🧪 Testing the API

The API can be tested using:

1.Swagger UI
2.Postman
3.cURL
4.PowerShell

Example:   curl -X GET \https://resourceful-youthfulness-production-8c81.up.railway.app/dashboard/summary \-H "Authorization: Bearer <TOKEN>"



🖥 Running the Project Locally

Clone the repository = git clone https://github.com/KunalPatil94/finance-dashboard-backend.git

Navigate to the project directory = cd finance-dashboard-backend


Configure MySQL

Create database

CREATE DATABASE finance_dashboard;

Update database settings in: src/main/resources/application.properties



Run Application -> mvn spring-boot:run


Server starts at : http://localhost:8080


Swagger UI : http://localhost:8080/swagger-ui/index.html



☁ Deployment

The project is deployed using Railway Cloud Platform.

Railway provides:

1.automatic deployments
2.managed MySQL database
3.environment variable configuration
4.GitHub integration

Production API: https://resourceful-youthfulness-production-8c81.up.railway.app


📈 Future Improvements

Planned enhancements:

1.Update financial records endpoint
2.Export reports (PDF / CSV)
3.Refresh token authentication
4.User management APIs
5.Advanced analytics charts
6.Unit and integration testing


👨‍💻 Author
Mr. Kunal R. Patil
Finance Dashboard Backend
Spring Boot REST API Project
April 2026

