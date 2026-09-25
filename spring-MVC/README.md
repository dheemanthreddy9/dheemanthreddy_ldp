# Spring MVC Practice Project 🚀

Welcome to your Spring MVC practice repository! This project is set up with **Spring MVC (Spring 6)** and embedded **Tomcat server**, making it easy to learn and practice RESTful Web APIs, `GET`, `POST`, `PUT`, and `DELETE` HTTP request mappings.

---

## 📁 Project Structure

```text
spring-MVC/
├── pom.xml                                      # Maven configuration (Spring MVC & Tomcat Embed)
├── spring-mvc-practice.postman_collection.json  # Pre-built Postman collection for API testing
├── README.md                                    # Practice guide & documentation
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    ├── Main.java                # Application Entry Point (Runs Embedded Tomcat Server)
                    ├── config/
                    │   ├── WebConfig.java       # Spring MVC Configuration (@EnableWebMvc, ComponentScan)
                    │   └── WebAppInitializer.java # Spring Web DispatcherServlet Initializer
                    ├── controller/
                    │   └── BookController.java  # REST Controller (@GetMapping, @PostMapping, etc.)
                    └── model/
                        └── Book.java            # Book Data Model POJO
```

---

## 🛠️ How to Run the Application

### Option 1: Run via IDE (IntelliJ IDEA / Eclipse / VS Code)
1. Open [Main.java](file:///c:/Users/2005a/Downloads/git%20practice/dheemanthreddy_ldp/spring-MVC/src/main/java/org/example/Main.java).
2. Right-click and select **Run 'Main.main()'**.
3. You will see output: `Spring MVC Application started successfully! Ready for Postman requests.`

### Option 2: Run via Terminal (Maven)
Run the following command in the project root folder:
```bash
mvn compile exec:java
```

The application will start on **`http://localhost:8080/`**.

---

## 📮 Testing with Postman

### Step 1: Import Postman Collection
1. Open **Postman**.
2. Click **Import** (top left).
3. Select or drag-and-drop the file: `spring-mvc-practice.postman_collection.json` located in this project directory.
4. You will see a collection named **Spring MVC Practice - Book API**.

---

### Step 2: Practice Endpoints Summary

| HTTP Method | URL Endpoint | Description | Spring Annotation |
|---|---|---|---|
| **GET** | `http://localhost:8080/books` | Get all books | `@GetMapping` |
| **GET** | `http://localhost:8080/books/1` | Get book by ID | `@GetMapping("/{id}")` + `@PathVariable` |
| **GET** | `http://localhost:8080/books/search?title=Clean` | Search books by title | `@GetMapping("/search")` + `@RequestParam` |
| **POST** | `http://localhost:8080/books` | Add new book | `@PostMapping` + `@RequestBody` |
| **PUT** | `http://localhost:8080/books/1` | Update existing book | `@PutMapping("/{id}")` + `@RequestBody` |
| **DELETE** | `http://localhost:8080/books/1` | Delete book by ID | `@DeleteMapping("/{id}")` + `@PathVariable` |

---

## 🎓 Quick Reference & Key Concepts

### 1. `@GetMapping`
Used to handle HTTP GET requests (retrieving data).
```java
@GetMapping
public List<Book> getAllBooks() {
    return books;
}
```

### 2. `@PathVariable`
Extracts values from the URI path.
```java
@GetMapping("/{id}")
public ResponseEntity<Book> getBookById(@PathVariable int id) { ... }
```

### 3. `@RequestParam`
Extracts query parameters from the URL (`?title=Clean`).
```java
@GetMapping("/search")
public List<Book> searchBooksByTitle(@RequestParam(name = "title") String title) { ... }
```

### 4. `@PostMapping` & `@RequestBody`
Used to handle HTTP POST requests (creating new data). `@RequestBody` automatically converts incoming JSON into a Java object.
```java
@PostMapping
public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
    books.add(newBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
}
```

---

Happy Coding & Practicing! 💡
