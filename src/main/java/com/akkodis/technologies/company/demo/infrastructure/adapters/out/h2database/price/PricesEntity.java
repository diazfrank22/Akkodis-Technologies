package com.akkodis.technologies.company.demo.infrastructure.adapters.out.h2database.price;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="prices")
public class PricesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Long priceList;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "priority")
    private String priority;
    @Column(name = "price")
    private Double price;
    @Column(name = "currency")
    private String currency;
}




