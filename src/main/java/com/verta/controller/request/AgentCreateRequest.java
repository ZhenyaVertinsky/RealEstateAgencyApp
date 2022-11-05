package com.verta.controller.request;

import lombok.Data;

@Data
public class AgentCreateRequest {

    private String agentName;

    private String agentSurname;

    private Integer percentReward;
}
