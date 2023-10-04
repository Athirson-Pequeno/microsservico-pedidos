package com.tizo.mspproducts.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/msp-products")
public class ProductsResource {

    @GetMapping(value = "/teste")
    public String teste(){
        return "teste products";
    }
}
