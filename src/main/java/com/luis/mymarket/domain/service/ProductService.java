package com.luis.mymarket.domain.service;

import com.luis.mymarket.domain.Product;
import com.luis.mymarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(long prodId){
        return productRepository.getProduct(prodId);
    }

    public Optional<List<Product>> getByCategory(long categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(long productId){
       return getProduct(productId).map(prod -> {
           productRepository.delete(productId);
           return true;
       }).orElse(false);

    }
}
