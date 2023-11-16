package com.tizo.msporders.service;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import com.tizo.msporders.entity.RequestRecord;
import com.tizo.msporders.entity.StatusOrder;
import com.tizo.msporders.repository.OrdersRepository;
import com.tizo.msporders.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private RestTemplate template;
    public Product getProductByID(Long id) throws NoSuchElementException{

            Product product = template.getForObject("http://localhost:8765/msp-products/id/" + id, Product.class);
            return product;

    }

    public Order saveOrder(List<RequestRecord> requestRecord, String clientEmail){

        Order order = new Order();
        UUID uuid = UUID.randomUUID();
        order.setId(uuid);

        requestRecord.forEach(item ->{

            Product product = getProductByID(item.productID());
            product.setAmount(item.amount());
            productRepository.save(product);
            order.getItens().add(product);

        });



        BigDecimal totalPrice = order.getItens().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        order.setPrice(totalPrice);
        order.setClientEmail(clientEmail);
        order.setDateOrderPlaced(LocalDateTime.now());
        order.setStatus(StatusOrder.REALIZADO.toString());

        ordersRepository.saveAndFlush(order);

        return ordersRepository.findById(uuid);
    }

    public void updateOrder(List<RequestRecord> requestRecord, UUID idUUID, String clientEmail){

        Order order = ordersRepository.findById(idUUID);
        if (!order.getClientEmail().equals(clientEmail)){
            throw new RuntimeException();
        }

        requestRecord.forEach(requestRecordItem -> {

        Product product = getProductByID(requestRecordItem.productID());

        order.getItens().forEach(item -> {
            if (item.equals(product)){

                item.setAmount(requestRecordItem.amount());
                productRepository.saveAndFlush(item);
            }
        });


        if (!order.getItens().contains(product)){

            product.setAmount(requestRecordItem.amount());
            productRepository.saveAndFlush(product);
            order.getItens().add(product);

        }
        });

        BigDecimal totalPrice = order.getItens().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        order.setPrice(totalPrice);
        order.setDateOrderPlaced(LocalDateTime.now());

        ordersRepository.saveAndFlush(order);
    }

}
