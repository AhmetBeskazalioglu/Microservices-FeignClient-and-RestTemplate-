package com.anke.shopping_cart_service_feignclient.controller;

import com.anke.shopping_cart_service_feignclient.entity.Product;
import com.anke.shopping_cart_service_feignclient.entity.ShoppingCartFeignClient;
import com.anke.shopping_cart_service_feignclient.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCartFeignClient> createCart(@RequestBody ShoppingCartFeignClient sc) {
        shoppingCartService.createCart(sc);
        return ResponseEntity.ok().body(sc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartFeignClient> getCartById(@PathVariable("id") Long id) {
        return shoppingCartService.getCartById(id);
    }

    @PostMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCartFeignClient> addExistingProductsToCart(@RequestBody List<Product> products,
                                                                             @PathVariable("shoppingCartId") Long shoppingCartId) {
        shoppingCartService.addExistingProductsToCart(products, shoppingCartId);
        return shoppingCartService.getCartById(shoppingCartId);
    }
}