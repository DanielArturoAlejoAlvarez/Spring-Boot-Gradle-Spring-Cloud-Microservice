package com.mediasoft.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Data
@Table(name = "tbl_customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The document number field must not be empty")
    @Size(min = 8, max = 8, message = "The size of the document number is 8")
    @Column(name = "number_id", length = 8, nullable = false, unique = true)
    private String numberID;

    @NotEmpty(message = "This field must not be empty!")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "This field must not be empty!")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "This field must not be empty!")
    @Email(message = "This field does not contain a well formed email")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "photo_url", length = 512)
    private String photoURL;

    @NotNull(message = "This field must not be empty!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    private String status;
}
