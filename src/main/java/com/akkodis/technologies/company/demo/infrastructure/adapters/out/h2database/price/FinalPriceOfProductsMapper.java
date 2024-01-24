package com.akkodis.technologies.company.demo.infrastructure.adapters.out.h2database.price;

import com.akkodis.technologies.company.demo.domain.entities.Price;

public class FinalPriceOfProductsMapper {

    public static Price entityToDomain(PricesEntity priceEntity) {

        return new Price(
                         priceEntity.getProductId()
                       , priceEntity.getBrandId()
                       , priceEntity.getPriceList()
                       , priceEntity.getStartDate()
                       , priceEntity.getPrice()
                       , priceEntity.getPriority());
    }

    public static PricesEntity domainToEntity(Price price) {

        return new PricesEntity( price.getPriceId()
                              , price.getBrandId()
                              , price.getStartDate()
                              , price.getEndDate()
                              , price.getPriceList()
                              , price.getProductId()
                              , price.getPriority()
                              , price.getPrice()
                              , price.getCurrency());
    }
}
