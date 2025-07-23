package com.library.service;

import com.library.model.User;

public interface UserService {
    User findByUsername(String username);
}
