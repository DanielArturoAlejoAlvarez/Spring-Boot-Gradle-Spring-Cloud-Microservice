package com.mediasoft.product;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import com.mediasoft.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    public void whenFindByCategory_thenReturnProductList() {
        Product prod1 = Product.builder()
                .name("iMac Pro")
                .description("")
                .stock(Double.parseDouble("500"))
                .price(Double.parseDouble("2789.25"))
                .image("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/imac-21-cto-hero-201903?wid=627&hei=522&fmt=jpeg&qlt=95&.v=1553120926388")
                .status("CREATED")
                .createdAt(new Date())
                .category(Category.builder().id(1L).build())
                .build();
        productRepository.save(prod1);

        List<Product> products = productRepository.findByCategory(prod1.getCategory());
        assertThat(products.size(), is(equalTo(3)));
    }
}