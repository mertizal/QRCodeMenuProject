package com.example.QRCodeMenu.repository;

import com.example.QRCodeMenu.model.Category;
import com.example.QRCodeMenu.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllCategoryByStoreId(Long storeId);
}
