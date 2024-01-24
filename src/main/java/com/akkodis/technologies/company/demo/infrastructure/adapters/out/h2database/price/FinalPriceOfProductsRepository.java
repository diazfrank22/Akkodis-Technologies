package com.akkodis.technologies.company.demo.infrastructure.adapters.out.h2database.price;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Configuration
public interface FinalPriceOfProductsRepository extends JpaRepository<PricesEntity,Long> {

    //Esta query me trae las tarifas que coincidan en un rango de fecha para luego tomar el de mayor prioridad
    /**
     SELECT p.*
     FROM PRICES p, PRICES p1
     WHERE p.PRODUCT_ID = 35455
     AND p.BRAND_ID = 1
     AND p1.PRIORITY < p.PRIORITY
     AND CAST(p.START_DATE AS DATE) = '2020-06-15'
     AND p.START_DATE <= p1.END_DATE
     AND p.END_DATE >= p1.START_DATE
     ORDER BY p.PRIORITY DESC
     */

    @Query("SELECT p FROM PricesEntity p, PricesEntity p1 " +
           "       WHERE p.brandId = :brandId " +
           "         AND p.productId = :productId " +
           "         AND CAST(p.startDate AS DATE) = :applicationDate" +
           "         AND p1.priority < p.priority" +
           "         AND p.startDate <= p1.endDate " +
           "         AND p.endDate >= p1.startDate " +
           "    ORDER BY p.priority DESC")
    Optional<List<PricesEntity>>  findFinalPriceByProductIdAndBrandIdAndAppDate(@Param("productId") Long productId,
                                                                                @Param("brandId") Long brandId,
                                                                                @Param("applicationDate") LocalDate applicationDate);

}
