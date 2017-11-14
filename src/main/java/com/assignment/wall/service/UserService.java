package com.assignment.wall.service;

import com.assignment.wall.app.WallController;
import com.assignment.wall.model.User;

import java.util.List;

/**
 * Created by adharamshi on 11/13/17.
 */
public interface UserService {

    User find(String id);

    List<User> findAll();

    User create(User user) throws UserIdAlreadyExistsException;

    User update(User user);

    void delete(String id);

    List<User> getRecommended10Users(User loginUser, WallController wallController);
}
