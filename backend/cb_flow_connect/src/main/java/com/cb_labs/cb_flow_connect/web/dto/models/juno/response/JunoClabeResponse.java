package com.cb_labs.cb_flow_connect.web.dto.models.juno.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record JunoClabeResponse(
    Boolean success,
    JunoClabePayload payload
) {
    public record JunoClabePayload(
        String clabe,
        String type
    ) { }
}
