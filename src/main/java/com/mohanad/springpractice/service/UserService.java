package com.mohanad.springpractice.service;

import com.mohanad.springpractice.entities.User;
import com.mohanad.springpractice.entities.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);

    void save(WebUser webUser);
}
