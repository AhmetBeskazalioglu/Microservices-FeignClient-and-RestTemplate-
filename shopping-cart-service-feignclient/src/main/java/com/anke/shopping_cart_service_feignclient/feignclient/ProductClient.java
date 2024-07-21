package com.anke.shopping_cart_service_feignclient.feignclient;

import com.anke.shopping_cart_service_feignclient.entity.ProductFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/product/{productId}")
    ProductFeignClient getProductById(@PathVariable("productId") Long productId);

}
