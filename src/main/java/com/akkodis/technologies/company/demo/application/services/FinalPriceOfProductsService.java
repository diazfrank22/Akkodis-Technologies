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

        return applyBusinessRuleForProductPrices(inputParametersport).entrySet()
                        .stream()
                        .map(entry -> new PriceDto(entry.getValue()))
                        .collect(Collectors.toList());
    }

    //PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).
    public Map<LocalDate, Price> applyBusinessRuleForProductPrices(FinalPriceOfProductsOutPort.Parameter inputParametersport) {

        return finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParametersport)
                .stream()
                .collect(Collectors.toMap(
                        price-> price.getStartDate().toLocalDate(),
                        price -> price,
                        (existente, nuevo) -> Integer.parseInt(existente.getPriority()) > Integer.parseInt(nuevo.getPriority()) ? existente : nuevo));

    }

}