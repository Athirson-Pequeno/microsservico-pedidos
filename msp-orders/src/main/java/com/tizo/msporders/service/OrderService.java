package com.tizo.msporders.service;

import com.tizo.msporders.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private RestTemplate template;
    public Product getProductByID(Long id){
        return template.getForObject("http://localhost:8765/msp-products/id/" + id, Product.class);
    }
}
