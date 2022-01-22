package com.ies.blueberry.repository;

import java.util.List;

import com.ies.blueberry.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByUser(String user);
}
