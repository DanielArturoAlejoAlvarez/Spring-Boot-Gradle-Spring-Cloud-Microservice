package com.mediasoft.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name field cannot be empty!")
    @Column(nullable = false)
    private String name;

    private String description;

    @Positive(message = "The stock field must be greater than zero")
    private Double stock;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image", length = 512)
    private String image;

    private String status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull(message = "The field name must not be null or empty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

}
