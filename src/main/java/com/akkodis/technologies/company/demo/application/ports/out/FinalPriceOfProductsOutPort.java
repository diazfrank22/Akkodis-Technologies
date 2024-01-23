package com.akkodis.technologies.company.demo.application.ports.out;

import com.akkodis.technologies.company.demo.domain.entities.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

public interface FinalPriceOfProductsOutPort {
    public List<Price> searchFinalPriceOfProducts(Parameter inputParameters);

    @Getter
    @Setter
    @AllArgsConstructor
    class Parameter {

        private LocalDate applicationDate;

        private Long productId;

        private Long brandId;

//        public Parameter(FinalPriceOfProductsInPort.Parameters inpputParameter) {
//
//            this.applicationDate = LocalDate.parse(inpputParameter.getApplicationDate());
//            this.productId = inpputParameter.getProductId();
//            this.brandId = inpputParameter.getBrandId();
//
//        }
    }
}
