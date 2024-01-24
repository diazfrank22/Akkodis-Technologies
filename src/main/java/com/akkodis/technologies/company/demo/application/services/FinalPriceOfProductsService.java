package com.akkodis.technologies.company.demo.application.services;

import com.akkodis.technologies.company.demo.application.ports.in.FinalPriceOfProductsInPort;
import com.akkodis.technologies.company.demo.application.ports.in.dtos.PriceDto;
import com.akkodis.technologies.company.demo.application.ports.out.FinalPriceOfProductsOutPort;
import com.akkodis.technologies.company.demo.common.UseCase;
import com.akkodis.technologies.company.demo.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
public class FinalPriceOfProductsService implements FinalPriceOfProductsInPort {

    @Autowired
    private final FinalPriceOfProductsOutPort finalPriceOfProductsPort;

    public FinalPriceOfProductsService(FinalPriceOfProductsOutPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @Override
    public List<PriceDto> searchFinalPriceOfProducts(Parameters inputParameters) {

        FinalPriceOfProductsOutPort.Parameter inputParametersport = new FinalPriceOfProductsOutPort.Parameter(LocalDate.parse(inputParameters.getApplicationDate()), inputParameters.getBrandId(), inputParameters.getProductId());

        return finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParametersport)
                                       .stream()
                                       .map(entry -> new PriceDto(entry))
                                       .collect(Collectors.toList());
    }

}
