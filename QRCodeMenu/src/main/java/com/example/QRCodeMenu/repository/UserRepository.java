package com.example.QRCodeMenu.repository;

import com.example.QRCodeMenu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long> {

    User findByEmailAndPassword(String email, String password);
    User findByAccessToken(String accessToken);
}
