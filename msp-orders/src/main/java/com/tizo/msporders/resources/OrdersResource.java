package com.tizo.msporders.resources;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import com.tizo.msporders.entity.RequestRecord;
import com.tizo.msporders.entity.StatusOrder;
import com.tizo.msporders.repository.ProductRepository;
import com.tizo.msporders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = ("msp-orders"))
public class OrdersResource {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    public OrderService orderService;
    @GetMapping(value = "/teste")
    public String teste(){
        return "teste orders";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> allOrders(){
        List<Order> list = orderService.getAllOrder();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> newOrder(@RequestBody RequestRecord requestRecord) {

        Order order = new Order();
        try {
            Product product = orderService.getProductByID(requestRecord.productID());
            productRepository.saveAndFlush(product);
            order.setPrice(product.getPrice());
            order.setClientID(requestRecord.clientID());
            order.getItens().add(product);
            order.setDateOrderPlaced(LocalDateTime.now());
            order.setStatus(StatusOrder.REALIZADO.toString());
            orderService.saveOrder(order);

        } catch (HttpClientErrorException.NotFound e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado, erro ao salvar pedido");
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }

        return ResponseEntity.ok("Pedido realizado");
    }
}
