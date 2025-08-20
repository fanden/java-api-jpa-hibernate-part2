package com.booleanuk.controller;

import com.booleanuk.model.Book;
import com.booleanuk.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    record PostBook(String title, String genre, int author_id, int publisher_id) {};

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookController.PostBook request){
        Book book = new Book(request.title, request.genre, request.author_id, request.publisher_id);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Integer id){
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody BookController.PostBook request){
        Book book = this.bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found")
        );

        book.setTitle(request.title);
        book.setGenre(request.genre);
        book.setAuthorId(request.author_id);
        book.setPublisherId(request.publisher_id);

        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable int id){
        Book book = this.bookRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        this.bookRepository.delete(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
