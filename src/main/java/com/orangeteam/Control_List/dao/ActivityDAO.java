package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.exception.EmptyIdException;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import java.util.List;
import java.util.Optional;

public interface ActivityDAO {
    List<Activity> getAll();
    List<Activity> getAllByUser(User user) throws EmptyIdException;
    Optional<Activity> getById(int activityId);
    Optional<Activity> addByUser(User user, Activity activity) throws EmptyIdException;
    int removeAllByUser(User user) throws EmptyIdException;
    int remove(Activity activity) throws EmptyIdException;
}
