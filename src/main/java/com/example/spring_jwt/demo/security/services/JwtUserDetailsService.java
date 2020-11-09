package com.example.spring_jwt.demo.security.services;

import java.util.ArrayList;

import com.example.spring_jwt.demo.models.UserDao;
import com.example.spring_jwt.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("codeyourempire".equals(username)) {
            return new User("codeyourempire", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Cannot find user with name " + username);
        }
    }


    public UserDao saveUser(UserDao user) {
        return repository.save(user);
    }
}
