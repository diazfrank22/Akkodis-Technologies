package com.akkodis.technologies.company.demo.infrastructure.adapters.out.h2database.price;

import com.akkodis.technologies.company.demo.application.ports.out.FinalPriceOfProductsOutPort;
import com.akkodis.technologies.company.demo.domain.entities.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class FinalPriceOfProductsAdapterTest {

    @InjectMocks
    FinalPriceOfProductsAdapter finalPriceOfProductsAdapter;

    @InjectMocks
    FinalPriceOfProductsMapper finalPriceOfProductsMapper;

    @Mock
    private FinalPriceOfProductsRepository finalPriceOfProductsRepository;

    @Test
    void getFinalPriceOfProductsTest(){

        //Given
        //out port parameters
        var parametersOutPort = new FinalPriceOfProductsOutPort.Parameter(LocalDate.parse("2020-06-14"), 35455l, 2l);

        var priceEntity = new PricesEntity(8l, 1L, LocalDateTime.parse("2020-08-20T15:00:00", DateTimeFormatter.ISO_DATE_TIME), LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_DATE_TIME), 2L, 35455L, "0", 25.45, "EUR");

        List<PricesEntity> pricesEntityList = new ArrayList<>();

        pricesEntityList.add(priceEntity);

        var expectedPricesEntity = finalPriceOfProductsMapper.entityToDomain(priceEntity);

        Mockito.when(finalPriceOfProductsRepository.findFinalPriceByProductIdAndBrandIdAndAppDate(anyLong(),anyLong(),any(LocalDate.class))).thenReturn(Optional.of(pricesEntityList));

         //When
        List<Price> responseExpectedPricesEntity = finalPriceOfProductsAdapter.searchFinalPriceOfProducts(parametersOutPort);

         //Then;
        assertEquals(expectedPricesEntity.toString(), responseExpectedPricesEntity.get(0).toString());
    }


    @Test
    public void validateResponseOfTheDateParameter() {

        //Given //input port parameters In  //Case 1

        //When //out port parameters //Case 1
        try{var parametersOutPort = new FinalPriceOfProductsOutPort.Parameter(LocalDate.parse("2020-06-1"),35455l,2l);}
        catch (DateTimeParseException e){
            //Then
            System.out.println(e.getMessage());
            assertEquals("Text '2020-06-1' could not be parsed at index 8", e.getMessage());
        }
    }
}
