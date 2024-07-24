package com.anke.E_Commerce_Web_Anke.controller;

import com.anke.E_Commerce_Web_Anke.entity.Product;
import com.anke.E_Commerce_Web_Anke.entity.ShoppingCart;
import com.anke.E_Commerce_Web_Anke.feignclient.ProductClient;
import com.anke.E_Commerce_Web_Anke.feignclient.ShoppingCartClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ShoppingCartClient shoppingCartClient;

    private Long currentCartId = 1L;

    @PostMapping("/add")
    public String addProductsToCart(@RequestParam("productId") Long productId) {
        Product product = productClient.getProductById(productId);
        shoppingCartClient.addExistingProductsToCart(List.of(product), currentCartId);
        return "redirect:/api/feign-client/home";
    }

    @GetMapping
    public String viewCart(Model model) {
        ResponseEntity<ShoppingCart> cartById = shoppingCartClient.getCartById(currentCartId);
        model.addAttribute("cart", cartById.getBody());
        return "cart";
    }

    @PostMapping("/{cartId}/product/{productId}/remove")
    public String removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartClient.removeProduct(cartId, productId);
        return "redirect:/cart";
    }

    @PostMapping("/{cartId}/products/remove")
    public String removeAllProductsFromCart(@PathVariable Long cartId) {
        shoppingCartClient.removeAllProducts(cartId);
        return "redirect:/cart";
    }
}