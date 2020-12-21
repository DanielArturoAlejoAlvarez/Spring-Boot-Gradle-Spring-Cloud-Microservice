package com.mediasoft.product.controller;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import com.mediasoft.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProductService service;

    /*@GetMapping
    public ResponseEntity<List<Product>> listProduct() {
        List<Product> products = service.listAllProduct();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }*/
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Product> products = new ArrayList<>();
        if (null==categoryId) {
            products = service.listAllProduct();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else {
            products = service.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }


}
