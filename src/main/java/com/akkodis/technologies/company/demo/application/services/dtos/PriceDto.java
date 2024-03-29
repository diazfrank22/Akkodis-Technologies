package com.akkodis.technologies.company.demo.application.services.dtos;


import com.akkodis.technologies.company.demo.domain.entities.Price;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PriceDto {

    private Long productid;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private Double price;

   public PriceDto(Price price){
       this.productid=price.getProductId();
       this.brandId=price.getBrandId();
       this.priceList=price.getPriceList();
       this.startDate=price.getStartDate();
       this.price=price.getPrice();
   }
}
