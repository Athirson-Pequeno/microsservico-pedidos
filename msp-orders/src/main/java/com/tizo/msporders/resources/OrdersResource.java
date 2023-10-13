package com.tizo.msporders.resources;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import com.tizo.msporders.entity.RequestRecord;
import com.tizo.msporders.entity.StatusOrder;
import com.tizo.msporders.repository.OrdersRepository;
import com.tizo.msporders.repository.ProductRepository;
import com.tizo.msporders.service.OrderService;
import com.tizo.msporders.util.JwtUtil;
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
import java.util.UUID;

@RestController
@RequestMapping(value = ("msp-orders"))
public class OrdersResource {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> allOrders(){
        List<Order> list = ordersRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<String> newOrder(@RequestBody List<RequestRecord> requestRecord, @RequestHeader String authorization) {

        String clientEmail = jwtUtil.getSubject(authorization);

        try {
            orderService.saveOrder(requestRecord, clientEmail);

        } catch (HttpClientErrorException.NotFound e){

            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado, erro ao salvar pedido");

        } catch (Exception e){

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");

        }

        return ResponseEntity.ok("Pedido realizado");
    }

    @PostMapping(value = "/orders/clientorders")
    public ResponseEntity<List<Order>> findByClientID(@RequestHeader String authorization){
        String clientEmail = jwtUtil.getSubject(authorization);
        List<Order> list = ordersRepository.findByClientEmail(clientEmail);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/orders/update/{idUUID}")
    public ResponseEntity<String> updateOrder(@RequestBody List<RequestRecord> requestRecord, @PathVariable UUID idUUID, @RequestHeader String authorization){

        String clientEmail = jwtUtil.getSubject(authorization);

        orderService.updateOrder(requestRecord,  idUUID , clientEmail);

        return ResponseEntity.ok("Pedido atualizado");
    }
}
