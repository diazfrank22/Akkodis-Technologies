package com.akkodis.technologies.company.demo.infrastructure.adapters.out.h2database.price;

import com.akkodis.technologies.company.demo.application.ports.out.FinalPriceOfProductsOutPort;
import com.akkodis.technologies.company.demo.common.PersistenceAdapter;
import com.akkodis.technologies.company.demo.domain.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class FinalPriceOfProductsAdapter implements FinalPriceOfProductsOutPort {

    @Autowired
    private final FinalPriceOfProductsRepository finalPriceOfProductsRepository;

    public FinalPriceOfProductsAdapter(FinalPriceOfProductsRepository finalPriceOfProductsRepository) {
        this.finalPriceOfProductsRepository = finalPriceOfProductsRepository;
    }


    @Override
    public List<Price> searchFinalPriceOfProducts(Parameter inputParameter) {


        Optional<List<PricesEntity>> result = finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(inputParameter.getBrandId(),
                inputParameter.getProductId(),
                inputParameter.getApplicationDate());

        
        //Mapeo el resultado de un entidad JPA a una entidad dominio
        return finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(inputParameter.getBrandId(),
                                                                                            inputParameter.getProductId(),
                                                                                            inputParameter.getApplicationDate())
                                             .get()
                                             .stream()
                                             .map(FinalPriceOfProductsMapper::entityToDomain)
                                             .collect(Collectors.toList());
    }
}