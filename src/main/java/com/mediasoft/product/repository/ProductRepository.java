package com.mediasoft.product.repository;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findByCategory(Category category);
}
