package com.example.token.jwt.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
public User findByUserNameAndPassword(String username, String password);
public User findByUserName(String username);
}