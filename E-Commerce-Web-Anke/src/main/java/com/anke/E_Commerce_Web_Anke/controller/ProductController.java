package com.anke.E_Commerce_Web_Anke.controller;

import com.anke.E_Commerce_Web_Anke.entity.Product;
import com.anke.E_Commerce_Web_Anke.feignclient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("api/feign-client")
public class ProductController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = productClient.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/home")
    public ModelAndView getProductsHome(Model model) {
        List<Product> products = productClient.getAllProducts();
        model.addAttribute("products", products);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        return modelAndView;
    }

}
