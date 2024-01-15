package com.example.pillawebappbooks.dao;

import com.example.pillawebappbooks.models.Book;
import com.example.pillawebappbooks.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Long> {
    public Book findByTitle(String title);
    public Book findByDescription(String description);
    public Optional<Book> findById(Long id);
}
