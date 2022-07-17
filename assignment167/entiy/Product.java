package com.example.assignment167.entiy;


import com.example.assignment167.entiy.base.BaseEntity;
import com.example.assignment167.entiy.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private String thumbnails;
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;
}
