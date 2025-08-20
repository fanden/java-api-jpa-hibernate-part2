package com.booleanuk.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "publisher_id")
    private int publisherId;

    public Book(String title, String genre, int authorId, int publisherId) {
        this.title = title;
        this.genre = genre;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }
}
