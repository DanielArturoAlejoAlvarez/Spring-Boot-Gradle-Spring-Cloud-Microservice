package com.mediasoft.product.client;

import com.mediasoft.product.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service")
@RequestMapping(value = "/'products")
public interface ProductClient {

    @GetMapping(name = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @GetMapping(name = "/{id}/stock")
    public ResponseEntity<Product> updStockProduct(@PathVariable("id") Long id, @RequestParam(name = "qty", required = true) Double qty);
}
