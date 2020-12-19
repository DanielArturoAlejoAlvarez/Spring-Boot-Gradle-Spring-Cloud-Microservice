package com.mediasoft.product.service;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;

import java.util.List;

public interface IProductService {

    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product p);
    public Product updateProduct(Product p);
    public Product deleteProduct(Long id);
    public List<Product> findByCategory(Category c);
    public Product updStock(Long id, Double qty);
}
