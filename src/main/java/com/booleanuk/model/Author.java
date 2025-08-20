package com.booleanuk.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "alive")
    private boolean alive;

    public Author(String firstName, String lastName, String email, boolean alive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.alive = alive;
    }

    public Author(int id, String firstName, String lastName, String email, boolean alive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.alive = alive;
    }
}
