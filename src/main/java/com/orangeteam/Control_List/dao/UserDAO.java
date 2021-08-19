package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.exception.EmptyIdException;
import com.orangeteam.Control_List.exception.UserUniqueViolationException;
import com.orangeteam.Control_List.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAll();
    Optional<User> getById(int userId);
    Optional<User> add(User user) throws UserUniqueViolationException;
    int update(User user) throws EmptyIdException, UserUniqueViolationException;
    int remove(int userId);
}
