package com.cb_labs.cb_flow_connect.web.dto.models.juno.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record JunoClabeResponse(
    String clabe,
    String type,
    String status,
    String createdAt,
    String updatedAt
) { }
