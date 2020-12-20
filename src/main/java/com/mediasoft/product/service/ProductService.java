package com.mediasoft.product.service;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import com.mediasoft.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    //@Autowired
    private final ProductRepository repo;

    @Override
    public List<Product> listAllProduct() {
        return repo.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product p) {
        p.setStatus("CREATED");
        p.setCreatedAt(new Date());
        return repo.save(p);
    }

    @Override
    public Product updateProduct(Product p) {
        Product prodDB = getProduct(p.getId());
        if (null==prodDB) {
            return null;
        }
        prodDB.setName(p.getName());
        prodDB.setDescription(p.getDescription());
        prodDB.setPrice(p.getPrice());
        prodDB.setImage(p.getImage());
        prodDB.setCategory(p.getCategory());

        return repo.save(prodDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product prodDB = getProduct(id);
        if (null==prodDB) {
            return null;
        }
        prodDB.setStatus("DELETED");

        return repo.save(prodDB);
    }

    @Override
    public List<Product> findByCategory(Category c) {
        return repo.findByCategory(c);
    }

    @Override
    public Product updStock(Long id, Double qty) {
        Product prodDB = getProduct(id);
        if (null==prodDB) {
            return null;
        }
        Double stock = prodDB.getStock() - qty;
        prodDB.setStock(stock);
        return repo.save(prodDB);
    }
}
