package com.booleanuk.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnoreProperties("{publisher}")
    private List<Book> books;

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }
    public Publisher(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    public Publisher(int id) {
        this.id = id;
    }
}
