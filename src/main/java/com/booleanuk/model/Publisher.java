package com.booleanuk.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }
    public Publisher(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
