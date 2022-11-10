package com.example.QRCodeMenu.api;

import com.example.QRCodeMenu.model.*;
import com.example.QRCodeMenu.servies.ApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RequestMapping("api")
@RestController
@Api(value = "User Api documentation")
public class ApiController {
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/stores")
    public void addStore(@RequestBody Store store, @RequestHeader("token") String token) {

        Long currentUserId = checkToken(token);
        if (!store.getUser().getId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401), "burada 1");
        }
        apiService.addStore(store);
    }

    @GetMapping("/stores")
    public List<Store> getAllStores() {

        return apiService.getAllStores();
    }

    @GetMapping("/stores/{storeId}")
    public Store getStoreById(@PathVariable("storeId") Long storeId, @RequestHeader("token") String token) {

        Store currentStore = checkStoreById(storeId);
        if (!currentStore.getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(404));
        }
        return apiService.getStoreById(storeId);
    }

    @DeleteMapping("/stores/{storeId}")
    public void deleteStoreById(@PathVariable("storeId") Long storeId) {

        apiService.deleteStoreById(storeId);
    }

    @PatchMapping("/stores/{storeId}")
    public Store updateStoreById(@Nullable @PathVariable("storeId") Long storeId, @RequestBody Map<Object, Object> objectsMap,
                                 @RequestHeader("token") String token) {
        Store currentStore = checkStoreById(storeId);
        if (!currentStore.getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return apiService.updateStoreById(storeId, objectsMap);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product, @RequestHeader("token") String token) {

        Long currentUserId = checkToken(token);
        if (!product.getStore().getUser().getId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        apiService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProductByStoreId(@Nullable @RequestParam(value = "storeId", required = false) Long storeId) {

        return apiService.getAllProductByStoreId(storeId);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productId, @RequestHeader("token") String token) {

        Product currentProduct = checkProductById(productId);
        if (!currentProduct.getStore().getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return apiService.getProductById(productId);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id") Long productId, @RequestHeader("token") String token) {

        Product currentProduct = checkProductById(productId);
        if (!currentProduct.getStore().getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        apiService.deleteProductById(productId);
    }

    @PatchMapping("/products/{id}")
    public Product updateProductById(@PathVariable("id") Long productId, @RequestBody Map<Object, Object> objectsMap,
                                     @RequestHeader("token") String token) {
        Product currentProduct = checkProductById(productId);
        if (!currentProduct.getStore().getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return apiService.updateProductById(productId, objectsMap);
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category, @RequestHeader("token") String token) {

        Long currentUserId = checkToken(token);
        if (!category.getStore().getUser().getId().equals(currentUserId)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401), "burada 1");
        }
        apiService.addCategory(category);
    }
    @GetMapping("/categories")
    public List<Category> getAllCategoryByStoreId(@Nullable @RequestParam(value = "storeId", required = false) Long storeId) {

        return apiService.getAllCategoryByStoreId(storeId);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable("id") Long categoryId, @RequestHeader("token") String token) {

        Category currentCategory = checkCategoryById(categoryId);
        if (!currentCategory.getStore().getUser().getAccessToken().equals(token)) {

            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return apiService.getCategoryById(categoryId);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@PathVariable("id") Long categoryId, @RequestHeader("token") String token) {

        Category currentCategory = checkCategoryById(categoryId);
        if (!currentCategory.getStore().getUser().getAccessToken().equals(token)) {

            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        apiService.deleteCategoryById(categoryId);
    }

    @PatchMapping("/categories/{id}")
    public Category updateCategoryById(@PathVariable("id") Long categoryId, @RequestBody Map<Object, Object> objectsMap,
                                       @RequestHeader("token") String token) {

        Category currentCategory = checkCategoryById(categoryId);
        if (!currentCategory.getStore().getUser().getAccessToken().equals(token)) {
            throw new ResponseStatusException(HttpStatus.valueOf(401));
        }
        return apiService.updateCategoryById(categoryId, objectsMap);
    }

    @PostMapping("/user")
    public String enterUser(@RequestBody LoginRequest loginRequest) {

        return apiService.enterUser(loginRequest);
    }

    private Long checkToken(String token) {

        return apiService.checkToken(token);
    }

    private Store checkStoreById(Long storeId) {

        return apiService.checkStoreById(storeId);
    }

    private Category checkCategoryById(Long categoryId) {

        return apiService.checkCategoryById(categoryId);
    }

    private Product checkProductById(Long productId) {

        return apiService.checkProductById(productId);
    }
}
