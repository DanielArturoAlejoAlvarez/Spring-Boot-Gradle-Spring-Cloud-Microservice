package com.mediasoft.product.service;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Override
    public List<Product> listAllProduct() {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product p) {
        return null;
    }

    @Override
    public Product updateProduct(Product p) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> findByCategory(Category c) {
        return null;
    }

    @Override
    public Product updStock(Long id, Double qty) {
        return null;
    }
}
