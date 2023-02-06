package com.example.weshare.Repositories;

import com.example.weshare.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
