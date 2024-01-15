package com.example.pillawebappbooks.dao;

import com.example.pillawebappbooks.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    @Query("select s from User s where username= :username and password = :password")
    public User login(String username, String password);
}
