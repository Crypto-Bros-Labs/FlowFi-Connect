package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.FiatCurrency;
import com.cb_labs.cb_flow_connect.persistance.respositories.IFiatCurrencyRepository;
import com.cb_labs.cb_flow_connect.service.IFiatCurrencyService;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.FiatCurrencyResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FiatCurrencyServiceImpl implements IFiatCurrencyService {

    @Autowired
    private IFiatCurrencyRepository repository;

    @Override
    public BaseResponse getAll() {
        List<FiatCurrencyResponse> fiatCurrencyResponses = repository.findAll()
                .stream().map(this::toFiatCurrencyResponse).toList();

        return BaseResponse.builder()
                .data(fiatCurrencyResponses)
                .message("Fiat currency successfully retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    @Override
    public FiatCurrency getFiatCurrencyByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    private FiatCurrencyResponse toFiatCurrencyResponse(FiatCurrency fiatCurrency) {
        return new FiatCurrencyResponse(
            fiatCurrency.getUuid(),
            fiatCurrency.getName(),
            fiatCurrency.getSymbol()
        );
    }

}
