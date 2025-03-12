E-Commerce Platform
A full-stack e-commerce application with product catalog, filtering, and shopping cart.
Features

Product catalog with different products across 6 categories
Search, filter by category, and sort by price
Shopping cart with add/remove functionality
Persistent cart using localStorage

Technology Stack

Backend: Java Spring Boot, MySQL, JPA/Hibernate
Frontend: React, Bootstrap

Setup

Backend
Copy# Create MySQL database named ecommerce_db
# Update application.properties with your DB credentials
./mvnw spring-boot:run

Frontend
Copycd frontend
npm install
npm start


Project Structure
Copy├── Backend: Spring Boot API (Java)
│   ├── Entity classes (Product, Category, Cart, CartItem)
│   ├── Repositories and Controllers
│   └── Data Seeder for sample products
│
└── Frontend: React (JavaScript)
    ├── Components: App, ProductList, CategoryFilter, Cart
    └── State management using React hooks
Future Plans

User authentication
Payment processing
Order history
