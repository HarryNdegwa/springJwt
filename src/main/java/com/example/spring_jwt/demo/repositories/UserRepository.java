package com.example.spring_jwt.demo.repositories;

import com.example.spring_jwt.demo.models.UserDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

}
