package com.example.springinaction.repository;

import com.example.springinaction.dao.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
