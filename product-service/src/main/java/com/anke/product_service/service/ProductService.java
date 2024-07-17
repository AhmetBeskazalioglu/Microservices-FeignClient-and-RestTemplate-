package com.anke.product_service.service;

import com.anke.product_service.entity.Product;
import com.anke.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    //database deki eklenen kayıt satırı nesneye dönüştürülüp bu metodu kim nerde
    //çağırıyorsa ona json olarak döner  {"name":"urun"}
    public Product createProduct(Product product) {
        //jdbc insert into product (name,price,description) values(product.name,product.price);
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found in DB"));
    }

    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok().body(productRepository.findAll());
    }

}
