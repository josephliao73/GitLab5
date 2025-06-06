# Compact Disc Management API

## Introduction

The Compact Disc Management API is a Spring Boot application that provides a RESTful interface for managing a collection of compact discs and their associated tracks. It allows users to perform CRUD (Create, Read, Update, Delete) operations on compact discs and tracks, making it suitable for applications such as music catalog management or inventory systems.

The application uses JPA (Java Persistence API) for database interactions, Swagger for API documentation, and supports features like caching and transactional operations.

---

## Features

- **Compact Disc Management**:
  - Add, update, retrieve, and delete compact discs.
  - Each compact disc includes details such as title, artist, price, and the number of tracks.

- **Track Management**:
  - Tracks are associated with compact discs and include details like title and a reference to the compact disc they belong to.

- **RESTful API**:
  - Exposes endpoints for managing compact discs and tracks.
  - Supports JSON-based requests and responses.

- **Swagger Integration**:
  - Provides interactive API documentation for exploring and testing endpoints.

- **Database Integration**:
  - Uses JPA to map entities (`CompactDisc` and `Track`) to database tables (`compact_discs` and `tracks`).

- **Transactional Operations**:
  - Ensures data consistency during database operations.

---

## API Endpoints

### Compact Disc Endpoints
- **GET `/api/compactdiscs`**: Retrieve all compact discs.
- **GET `/api/compactdiscs/{id}`**: Retrieve a specific compact disc by its ID.
- **POST `/api/compactdiscs`**: Add a new compact disc.
- **DELETE `/api/compactdiscs/{id}`**: Delete a compact disc by its ID.
- **DELETE `/api/compactdiscs`**: Delete a compact disc by passing its details in the request body.

### Track Endpoints
- Tracks are managed as part of the `CompactDisc` entity and are associated via the `cd_id` field.

---

## Technologies Used

- **Spring Boot**: Framework for building the application.
- **JPA (Java Persistence API)**: For database interactions.
- **Swagger**: For API documentation and testing.
- **Log4j**: For logging application events.
- **H2 Database (or other supported databases)**: For persisting data.