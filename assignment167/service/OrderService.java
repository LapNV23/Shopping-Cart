package com.example.assignment167.service;


import com.example.assignment167.entiy.Order;
import com.example.assignment167.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
//    final OrderRepository orderRepository;
//    public OrderService(OrderRepository orderRepository){
//        this.orderRepository = orderRepository;
//    }
    @Autowired
    private OrderRepository orderRepository;
    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }
    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public void deleteById(String id){
        orderRepository.deleteById(id);
    }
}
