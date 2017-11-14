package com.assignment.wall.repository;

import com.assignment.wall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>{
    //User findFirstByUserId(String userId);
}
