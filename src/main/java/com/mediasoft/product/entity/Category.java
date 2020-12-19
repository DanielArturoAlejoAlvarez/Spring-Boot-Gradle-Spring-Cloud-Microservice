package com.mediasoft.product.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
