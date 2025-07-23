package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.FiatCurrency;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.FiatCurrencyResponse;

import java.util.UUID;

public interface IFiatCurrencyService {
    BaseResponse<FiatCurrencyResponse> getAll();
    FiatCurrency getFiatCurrencyByUuid(UUID uuid);
}
