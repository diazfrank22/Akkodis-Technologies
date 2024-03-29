package com.akkodis.technologies.company.demo.infrastructure.adapters.in.apirest.price;

import com.akkodis.technologies.company.demo.application.ports.in.FinalPriceOfProductsInPort;
import com.akkodis.technologies.company.demo.application.ports.in.dtos.PriceDto;
import com.akkodis.technologies.company.demo.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@WebAdapter
@RestController
@RequestMapping("api/prices")
public class FinalPriceOfProductsController {

    @Autowired
    private final FinalPriceOfProductsInPort finalPriceOfProductsPort;

    public FinalPriceOfProductsController(FinalPriceOfProductsInPort finalPriceOfProductsPort) {
        this.finalPriceOfProductsPort = finalPriceOfProductsPort;
    }

    @GetMapping
    public List<ResponseEntity> searchFinalPriceOfProducts(@Valid @RequestBody FinalPriceOfProductsInPort.Parameters bodyParameterInput, BindingResult bindingResultValidation){

        FinalPriceOfProductsInPort.Parameters inputParameters = new FinalPriceOfProductsInPort.Parameters(bodyParameterInput);

        Optional<List<PriceDto>> finalProductPriceResponse = Optional.ofNullable(finalPriceOfProductsPort.searchFinalPriceOfProducts(inputParameters));

        //input parameter validation
        if (bindingResultValidation.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResultValidation.getFieldErrors().forEach(error ->errores.put(error.getField (),error.getDefaultMessage()));
            return Collections.singletonList(new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST));
        }

        //response validation
        if (!finalProductPriceResponse.isPresent()) {
            return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.NOT_FOUND));
        }

        if (finalProductPriceResponse.get().isEmpty()){
            return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.NO_CONTENT));
        }

        return Collections.singletonList(new ResponseEntity<>(finalProductPriceResponse, HttpStatus.OK));

    }
}