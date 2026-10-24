# productService
Learning to create a product service using java

### What this does
* Manage products and categories for the ecommerce store
* Create, read, update, delete products
* Paging and sorting on product listing
* Redis caching for faster product fetching
* Search products by name or category
* Validates user token by calling userService before serving requests

### Requirements
* Product CRUD apis
* Category management
* Paging and sorting on get all products
* Redis cache integration on getProductById and getAllProducts
* Soft delete using isDeleted flag
* OAuth2 resource server to validate jwt tokens from userService

### How to run
* Make sure mysql is running on port 3306
* Make sure redis is running on port 6379
* Create database productservice in mysql
* Run ProductServiceApplication.java from intellij
* Service starts on port 8181
