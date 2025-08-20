package com.booleanuk.controller;

import com.booleanuk.model.Publisher;
import com.booleanuk.model.Publisher;
import com.booleanuk.repository.BookRepository;
import com.booleanuk.repository.PublisherRepository;
import com.booleanuk.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("publishers")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private BookRepository bookRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    record PostPublisher(String name, String location) {};

    @GetMapping
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody PublisherController.PostPublisher request){
        Publisher publisher = new Publisher(request.name,  request.location);
        return new ResponseEntity<>(publisherRepository.save(publisher), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getById(@PathVariable("id") Integer id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found")
        );
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable int id, @RequestBody PublisherController.PostPublisher request){
        Publisher publisher = this.publisherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found")
        );

        publisher.setName(request.name);
        publisher.setLocation(request.location);

        return new ResponseEntity<>(publisherRepository.save(publisher), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> delete(@PathVariable int id){
        Publisher publisher = this.publisherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        bookRepository.deleteAll(publisher.getBooks());
        this.publisherRepository.delete(publisher);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }
}
