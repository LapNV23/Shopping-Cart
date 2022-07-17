package com.example.assignment167.restapi;


import com.example.assignment167.entiy.Order;
import com.example.assignment167.service.CategoryService;
import com.example.assignment167.service.OrderService;
import com.example.assignment167.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderApi {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Order>> getList(){
        return ResponseEntity.ok(orderService.findAll());
    }
}
