package com.mediasoft.product;

import com.mediasoft.product.entity.Category;
import com.mediasoft.product.entity.Product;
import com.mediasoft.product.repository.ProductRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.mediasoft.product.service.IProductService;
import com.mediasoft.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository repo;

    private IProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new ProductService(repo);
        Product computer = Product.builder()
                .id(1L)
                .code("C-741")
                .name("Notebook Pro")
                .description("")
                .stock(Double.parseDouble("500"))
                .price(Double.parseDouble("2785.25"))
                .image("")
                .category(Category.builder().id(1L).build())
                .build();

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(computer));
        Mockito.when(repo.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct() {
        Product result = service.getProduct(1L);
        assertThat(result.getName(), is(equalTo("Notebook Pro")));
    }

    public void whenValidUpdateStock_ThenReturnNewStock() {
        Product newStock = service.updStock(1L,Double.parseDouble("10"));
        assertThat(newStock.getStock(), is(equalTo(490)));
    }

}
