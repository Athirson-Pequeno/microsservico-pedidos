package com.tizo.mspproducts.resources;

import com.tizo.mspproducts.entity.Product;
import com.tizo.mspproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/msp-products")
public class ProductsResource {

        @Autowired
        private ProductRepository repository;

        @GetMapping
        public ResponseEntity<List<Product>> findAll(){
            List<Product> list = repository.findAll();
            return ResponseEntity.ok(list);
        }

        @GetMapping(value = "/id/{id}")
        public ResponseEntity<Product> findById(@PathVariable Long id){
            Product product = repository.findById(id).get();
            return ResponseEntity.ok(product);

        }


        @GetMapping(value = "/search")
        public ResponseEntity<Product> findByName(@RequestParam String name){
            Product obj = repository.findByName(name);
            return ResponseEntity.ok(obj);

        }

        @PostMapping(value = "/new")
        public void newProduct(@RequestBody Product product) {

            repository.saveAndFlush(product);

        }
}
