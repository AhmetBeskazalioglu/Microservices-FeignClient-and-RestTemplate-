package com.anke.E_Commerce_Web_Anke.feignclient;

import com.anke.E_Commerce_Web_Anke.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/product")
    List<Product> getAllProducts();
}
