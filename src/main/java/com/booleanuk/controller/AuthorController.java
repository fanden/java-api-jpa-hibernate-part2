package com.booleanuk.controller;

import com.booleanuk.model.Author;
import com.booleanuk.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    record PostAuthor(String first_name, String last_name, String email, boolean alive) {};

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody PostAuthor request){
        Author author = new Author(request.first_name,  request.last_name, request.email, request.alive);
        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") Integer id){
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found")
        );
        return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable int id, @RequestBody PostAuthor request){
        Author author = this.authorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found")
        );

        author.setFirstName(request.first_name);
        author.setLastName(request.last_name);
        author.setEmail(request.email);
        author.setAlive(request.alive);

        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable int id){
        Author author = this.authorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        this.authorRepository.delete(author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
