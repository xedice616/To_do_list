#  To-Do List REST API

A Spring Boot REST API for managing daily tasks with user authentication, task filtering, dashboard statistics, and soft delete functionality.

##  Features

### Authentication
- User Registration
- User Login
- Password Encryption using BCrypt
- Spring Security Authentication

### Task Management
- Create Task
- Update Task
- Delete Task (Soft Delete)
- Get Task by ID
- Get All Tasks

### Search & Filter
- Search by Title
- Search by Description
- Filter by Status
- Filter by Priority
- Filter by Due Date

### Additional Features
- Toggle Favorite
- Archive Task
- Dashboard Statistics
- Pagination
- Validation
- Exception Handling
- Swagger Documentation

---

#  Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Hibernate
- Lombok
- MapStruct
- Swagger (OpenAPI)
- Maven


---

#  REST Endpoints

## Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login user |

---

## Task

| Method | Endpoint |
|---------|----------|
| POST | /api/tasks |
| GET | /api/tasks |
| GET | /api/tasks/{id} |
| PUT | /api/tasks/{id} |
| DELETE | /api/tasks/{id} |

---

## Search

| Method | Endpoint |
|---------|----------|
| GET | /api/tasks/search/title |
| GET | /api/tasks/search/description |

---

## Filter

| Method | Endpoint |
|---------|----------|
| GET | /api/tasks/status |
| GET | /api/tasks/priority |
| GET | /api/tasks/due-date |

---

## Other Operations

| Method | Endpoint |
|---------|----------|
| PATCH | /api/tasks/{id}/favorite |
| PATCH | /api/tasks/{id}/archive |
| GET | /api/tasks/favorites |
| GET | /api/tasks/archived |
| GET | /api/tasks/dashboard |

---

# ⚙️ Installation

Clone the repository

```bash
git clone https://github.com/xedice616/To_do_list.git
```

Open the project

```bash
cd To_do_list
```

Configure PostgreSQL

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/todolist
    username: postgres
    password: your_password
```

Run the application

```bash
mvn spring-boot:run
```

---

# API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

#  Contributors

- Xədicə Əhmədova
- Telman Əsgərov

---

# 📄 License

This project was developed for educational purposes.
