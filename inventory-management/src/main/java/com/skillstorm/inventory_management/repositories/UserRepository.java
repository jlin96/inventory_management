package com.skillstorm.inventory_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.inventory_management.models.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{


    //Returns list of users with same email
    List<User> findByEmail(String email);
}
