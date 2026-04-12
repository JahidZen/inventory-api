# Inventory Management API

A production-ready RESTful API for managing products, categories, and orders.  
Built with Kotlin and Spring Boot, containerized with Docker, and deployed on AWS EC2 using a complete CI/CD pipeline.

---

## 🌐 Live API

Base URL:  
http://51.20.109.64:8080/api  

### Sample Endpoints:
- GET /products  
- GET /categories  
- GET /orders  
- GET /ping  

Example:  
http://51.20.109.64:8080/api/products  

---

## Features

- RESTful CRUD operations for products, categories, and orders  
- PostgreSQL database integration  
- Dockerized application using docker-compose  
- CI/CD pipeline with GitHub Actions  
- Automated deployment to AWS EC2  
- Health check endpoint for monitoring  

---

## 🛠️ Tech Stack

- Kotlin  
- Spring Boot  
- PostgreSQL  
- Docker & Docker Compose  
- GitHub Actions (CI/CD)  
- AWS EC2  

---

## ⚙️ CI/CD Pipeline

This project includes a fully automated CI/CD pipeline:

1. Code pushed to GitHub  
2. GitHub Actions builds and tests the application  
3. Docker image is built and pushed to Docker Hub  
4. EC2 server pulls the latest image  
5. Application is restarted automatically  

---

## Running Locally

Clone the repository:

```bash
git clone https://github.com/JahidZen/inventory-api.git
cd inventory-api
Build the project: ./gradlew build
Run with docker: docker compose -up --build



**API Design**

The application follows a layered architecture:

Controller → Handles HTTP requests
Service → Business logic
Repository → Database interaction


**Future Improvements**
Add authentication & authorization (JWT)
Pagination & filtering
API documentation (Swagger/OpenAPI)
Versioned Docker images


**Author**

Jahid
GitHub: https://github.com/JahidZen
