package com.cb_labs.cb_flow_connect.persistance.specifications;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import org.springframework.data.jpa.domain.Specification;

public class TokenNetworkSpecifications {

    public static Specification<TokenNetwork> networkEquals(Network network) {
        return (root, query, criteriaBuilder) -> {
            if (network == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("network"), network);
        };
    }

}
