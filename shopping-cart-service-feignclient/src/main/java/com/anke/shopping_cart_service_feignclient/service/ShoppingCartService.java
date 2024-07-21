package com.anke.shopping_cart_service_feignclient.service;

import com.anke.shopping_cart_service_feignclient.entity.ProductFeignClient;
import com.anke.shopping_cart_service_feignclient.entity.ShoppingCartFeignClient;
import com.anke.shopping_cart_service_feignclient.feignclient.ProductClient;
import com.anke.shopping_cart_service_feignclient.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ResponseEntity<ShoppingCartFeignClient> createCart(ShoppingCartFeignClient sc){
        shoppingCartRepository.save(sc);
        return ResponseEntity.ok(sc);
    }

    public ResponseEntity<ShoppingCartFeignClient> getCartById(Long id){
        ShoppingCartFeignClient shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        return ResponseEntity.ok(shoppingCart);
    }

    public ResponseEntity<ShoppingCartFeignClient> addExistingProductsToCart(List<ProductFeignClient> products, Long shoppingCartId) {

        ShoppingCartFeignClient shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));

        products.forEach(product -> {
            ProductFeignClient productFeignClient = productClient.getProductById(product.getId());
            if (productFeignClient != null) {
                shoppingCart.getProducts().add(productFeignClient);
            } else {
                throw new RuntimeException("Product not found with id: " + product.getId());
            }
        });
        shoppingCartRepository.save(shoppingCart);

        return ResponseEntity.ok(shoppingCart);
    }
}