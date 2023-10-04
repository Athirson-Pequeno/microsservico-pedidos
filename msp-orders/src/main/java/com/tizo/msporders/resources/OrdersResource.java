package com.tizo.msporders.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ("msp-orders"))
public class OrdersResource {

    @GetMapping(value = "/teste")
    public String teste(){
        return "teste orders";
    }
}
