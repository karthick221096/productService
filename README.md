
# productService

Product catalogue service for the e-commerce platform. It is an OAuth2 resource
server: reads are public and writes require a JWT issued by userService.

## What it does
- CRUD for products and categories with DTOs
- Paging and sorting on the product list
- Soft delete (`isDeleted` flag) with a delete endpoint
- Global exception handling (`ProductNotFoundException` returns 404)
- Database versioning with Flyway, JPA with MySQL

## Endpoints
| Method | Path | Auth |
  |---|---|---|
| GET | `/product/{id}` | Public |
| GET | `/products/?pageNumber=0&pageSize=10&sortBy=id` | Public |
| POST | `/product/` | JWT |
| PATCH | `/product/{id}` | JWT |
| DELETE | `/product/{id}` | JWT (soft delete) |

## Tech
Java 17, Spring Boot 3.x, Spring Data JPA, Flyway, MySQL,
Spring Security OAuth2 resource server.

## Run
1. Start MySQL and create the database `productservice`.
2. Provide the datasource username and password through environment variables
   or a local, uncommitted `application.properties`.
3. Start userService on port 9091 first. The JWT issuer is `http://localhost:9091`.
4. `./mvnw spring-boot:run` (the service starts on port 8181).
