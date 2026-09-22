package org.example.controller;

import org.example.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "Atomic Habits", "James Clear"));
        books.add(new Book(2, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(3, "Clean Code", "Robert Martin"));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") int id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam(name = "title") String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        // Simple auto ID assignment if id is not provided or 0
        if (newBook.getId() <= 0) {
            int maxId = books.stream().mapToInt(Book::getId).max().orElse(0);
            newBook.setId(maxId + 1);
        }
        books.add(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(name = "id") int id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book existing = books.get(i);
            if (existing.getId() == id) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return ResponseEntity.ok(updatedBook);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id") int id) {
        boolean removed = books.removeIf(b -> b.getId() == id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}