package com.example.spring_jwt.demo.security.services;

import java.util.ArrayList;
// import java.util.Optional;

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

        UserDao user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot find user with name " + username);
        } else {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
    }

    public UserDao saveUser(UserDao user) {
        return repository.save(user);
    }
}
