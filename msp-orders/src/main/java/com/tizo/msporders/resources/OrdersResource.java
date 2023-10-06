package com.tizo.msporders.resources;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import com.tizo.msporders.entity.RequestRecord;
import com.tizo.msporders.entity.StatusOrder;
import com.tizo.msporders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = ("msp-orders"))
public class OrdersResource {


    @Autowired
    public OrderService orderService;
    @GetMapping(value = "/teste")
    public String teste(){
        return "teste orders";
    }

    @PostMapping(value = "/new")
    public String newOrder(@RequestBody RequestRecord requestRecord){

        Order order = new Order();
        order.setClientID(requestRecord.clientID());
        order.getItens().add(orderService.getProductByID(requestRecord.productID()));
        order.setDateOrderPlaced(LocalDateTime.now());
        order.setStatus(StatusOrder.REALIZADO);

        return "pedido feito";

    }
}
