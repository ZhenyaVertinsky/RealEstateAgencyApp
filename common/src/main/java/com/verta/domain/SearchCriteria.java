package com.verta.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCriteria {

    private String agentName;

    private String agentSurname;

    private Long lowerBoundUserId;

    private Integer limit;

    private Integer offset;
}
