package com.mediasoft.product.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tbl_invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "The field must be positive!")
    private Double qty;

    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal() {
        if (this.price > 0 && this.qty > 0) {
            return this.price * this.qty;
        }else {
            return (double) 0;
        }
    }

    public InvoiceItem() {
        this.qty = (double) 0;
        this.price = (double) 0;
    }
}
