package com.example.assignment167.service;


import com.example.assignment167.entiy.Category;
import com.example.assignment167.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }
    //    public Product save(Product product){
//        return productRepository.save(product);
//    }
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(String id){
        categoryRepository.deleteById(id);
    }
}
