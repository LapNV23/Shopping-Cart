package com.example.assignment167.restapi;

import com.example.assignment167.entiy.Category;
import com.example.assignment167.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryApi {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> getList(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> create(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Category> delete(@PathVariable String id){
        if (!categoryService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();
        existCategory.setId(category.getId());
        existCategory.setName(category.getName());
        return ResponseEntity.ok(categoryService.save(existCategory));
    }
}
