package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.exception.EmptyIdException;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;

import java.util.List;
import java.util.Optional;

public interface ActivityDAO {
    List<Activity> getAll();
    List<Activity> getAllByUser(User user);
    Optional<Activity> getById(int activityId);
    Optional<Activity> addByUser(User user, Activity activity) throws EmptyIdException;
    int removeAllByUserId(int userId);
    int update(Activity activity) throws EmptyIdException;
    int remove(int activityId);
}
