package com.quqiququai.service;

import com.quqiququai.po.User;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);

    User getAdverseUser(Long id);

    List<User> getUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User updateUser(User user);

}
