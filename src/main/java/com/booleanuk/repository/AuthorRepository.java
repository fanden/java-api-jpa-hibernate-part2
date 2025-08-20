package com.booleanuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booleanuk.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
