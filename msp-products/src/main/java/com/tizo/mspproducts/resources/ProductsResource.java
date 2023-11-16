package com.tizo.mspproducts.resources;

import com.tizo.mspproducts.entity.Product;
import com.tizo.mspproducts.repository.ProductRepository;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
            try {
                Product product = repository.findById(id).get();
                return ResponseEntity.ok(product);
            }catch (Exception e){

                return ResponseEntity.notFound().build();
            }
        }
        @GetMapping(value = "/search")
        public ResponseEntity<Product> findByName(@RequestParam String name){
            Product obj = repository.findByName(name);
            return ResponseEntity.ok(obj);

        }

        @PostMapping(value = "/new")
        public ResponseEntity<String> newProduct(@RequestBody Product product) {

            try {
                repository.saveAndFlush(product);
                return ResponseEntity.ok("Product created");
            }catch (Exception e){
                return  ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error creating product");
            }

        }
}
