package com.example.QRCodeMenu.servies;

import com.example.QRCodeMenu.model.Category;
import com.example.QRCodeMenu.model.LoginRequest;
import com.example.QRCodeMenu.model.Product;
import com.example.QRCodeMenu.model.Store;
import com.example.QRCodeMenu.model.User;
import com.example.QRCodeMenu.repository.CategoryRepository;
import com.example.QRCodeMenu.repository.ProductRepository;
import com.example.QRCodeMenu.repository.StoreRepository;
import com.example.QRCodeMenu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    public Store addStore(Store store) {

        if (Objects.isNull(store.getId())) {

            store.setId(store.getId());
        }

        return storeRepository.save(store);
    }

    public List<Store> getAllStores() {

        return storeRepository.findAll();
    }

    public Store getStoreById(Long storeId) {

        Store currentStore = storeRepository.findById(storeId).orElse(null);
        if (currentStore != null) {

            return currentStore;
        }
        throw new IllegalStateException("bulunamadı.");
    }

    public void deleteStoreById(Long storeId) {

        storeRepository.deleteById(storeId);
    }

    public Store updateStoreById(Long storeId, Map<Object, Object> objectsMap) {

        Store currentStore = storeRepository.findById(storeId).orElse(null);
        if (Objects.nonNull(currentStore)) {

            objectsMap.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Store.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, currentStore, value);

            });
            return storeRepository.save(currentStore);
        }
        throw new ResponseStatusException(HttpStatus.valueOf(404));
    }

    public Product addProduct(Product product) {

        if (Objects.isNull(product.getId())) {
            product.setId(product.getId());

        }
        return productRepository.save(product);
    }

    public List<Product> getAllProductByStoreId(Long storeId) {

        if (storeId == null) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return productRepository.findAllByStoreId(storeId);
    }

    public Product getProductById(Long productId) {

        Product currentProduct = productRepository.findById(productId).orElse(null);
        if (currentProduct != null) {
            return currentProduct;
        }
        throw new IllegalStateException("bulunamadı.");
    }

    public void deleteProductById(Long productId) {

        productRepository.deleteById(productId);
    }

    public Product updateProductById(Long productId, Map<Object, Object> objectMap) { // burada ki map fonksiyonunda price alanın tipi float olarak alındığında patch method çalışmıyor. double olarak alındığında bir sıkıntı yok neden?

        Product currentProduct = checkProductById(productId);

        if (Objects.nonNull(currentProduct)) {

            objectMap.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class,(String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, currentProduct, value);
            });

            return productRepository.save(currentProduct);
        }

        throw new ResponseStatusException(HttpStatus.valueOf(404));
    }

    public Category addCategory(Category category) {

        if (Objects.isNull(category.getId())) {

            category.setId(category.getId());
        }
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategoryByStoreId(Long storeId) {

        if (storeId == null) {

            throw new ResponseStatusException(HttpStatus.valueOf(404));
        }

        return categoryRepository.findAllCategoryByStoreId(storeId);
    }

    public Category getCategoryById(Long categoryId) {

        Category currentCategory = categoryRepository.findById(categoryId).orElse(null);

        if (currentCategory != null) {

            return currentCategory;
        }

        throw new IllegalStateException(categoryId + " bu id'ye sahip bir category bulunamadı.");
    }

    public void deleteCategoryById(Long categoryId) {

        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategoryById(Long categoryId, Map<Object, Object> objectsMap) {

        Category currentCategory = categoryRepository.findById(categoryId).orElse(null);
        if (Objects.nonNull(currentCategory)) {
            objectsMap.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Category.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, currentCategory, value);
            });
            return categoryRepository.save(currentCategory);
        }
        throw new ResponseStatusException(HttpStatus.valueOf(404));
    }

    public String enterUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (Objects.nonNull(user)) {
            String token = UUID.randomUUID().toString();
            user.setAccessToken(token);
            userRepository.save(user);

            return token;
        }
        return "";
    }

    public Long checkToken(String token) {

        User user = userRepository.findByAccessToken(token);
        if (user != null) {
            return user.getId();
        }
        throw new ResponseStatusException(HttpStatus.valueOf(401));
    }

    public Store checkStoreById(Long storeId) {
        Store currentStore = storeRepository.findById(storeId).orElse(null);
        if (Objects.nonNull(currentStore)) {
            return currentStore;
        }
        throw new ResponseStatusException(HttpStatus.valueOf(401), "bu id'ye sahip bir Store yok. ");
    }

    public Category checkCategoryById(Long categoryId) {
        Category currentCategory = categoryRepository.findById(categoryId).orElse(null);
        if (Objects.nonNull(currentCategory)) {
            return currentCategory;
        }
        throw new ResponseStatusException(HttpStatus.valueOf(401), "bu id'ye sahip bir Category yok. ");
    }

    public Product checkProductById(Long productId) {
        Product currentProduct = productRepository.findById(productId).orElse(null);
        if (Objects.nonNull(currentProduct)) {
            return currentProduct;
        }
        throw new ResponseStatusException(HttpStatus.valueOf(401), "bu id'ye sahip bir Product yok. ");
    }
}
