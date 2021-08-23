package com.study.mint.mintweb.service;

import java.util.List;
import com.study.mint.mintweb.User;

public interface UserService {
    List < User > getAllUsers();
    void save(User user);
    User getUserById(long id);
    void deleteUserById(long id);
}
