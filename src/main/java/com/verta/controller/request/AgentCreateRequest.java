package com.verta.controller.request;

import lombok.Data;

@Data
public class AgentCreateRequest {

    private String userName;

    private String surname;

    private Integer percentReward;
}
