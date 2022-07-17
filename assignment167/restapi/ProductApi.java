package com.example.assignment167.restapi;


import com.example.assignment167.entiy.Category;
import com.example.assignment167.entiy.Product;
import com.example.assignment167.service.CategoryService;
import com.example.assignment167.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    public ProductApi(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getList(){
        return ResponseEntity.ok(productService.findAll());
    }

    // Path Variable
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product, @PathVariable String cateId) {
        Optional<Product> optionalProduct = productService.findById(id);
        Optional<Category> optionalCategory = categoryService.findById(cateId);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();
        // map object
        existProduct.setId(product.getId());
        existProduct.setName(product.getName());
        existProduct.setSlug(product.getSlug());
        existProduct.setDescription(product.getDescription());
        existProduct.setDetail(product.getDetail());
        existProduct.setThumbnails(product.getThumbnails());
        existProduct.setPrice(product.getPrice());
        existProduct.setCreatedAt(product.getCreatedAt());
        existProduct.setUpdatedAt(product.getUpdatedAt());
        existProduct.setCategory(optionalCategory.get());
        existProduct.setStatus(product.getStatus());
        return ResponseEntity.ok(productService.save(existProduct));
    }
}
