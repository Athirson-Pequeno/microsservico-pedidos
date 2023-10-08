package com.tizo.msporders.service;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import com.tizo.msporders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    public OrdersRepository ordersRepository;
    @Autowired
    private RestTemplate template;
    public Product getProductByID(Long id) throws NoSuchElementException{

            Product product = template.getForObject("http://localhost:8765/msp-products/id/" + id, Product.class);
            return product;

    }

    public void saveOrder(Order order){
        ordersRepository.save(order);
    }

    public List<Order> getAllOrder(){
        return ordersRepository.findAll();

    }
}
