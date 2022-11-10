package com.example.QRCodeMenu.repository;

import com.example.QRCodeMenu.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
