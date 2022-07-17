package com.example.assignment167.service;


import com.example.assignment167.entiy.Product;
import com.example.assignment167.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }
//    public Product save(Product product){
//        return productRepository.save(product);
//    }
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(String id){
        productRepository.deleteById(id);
    }
}
