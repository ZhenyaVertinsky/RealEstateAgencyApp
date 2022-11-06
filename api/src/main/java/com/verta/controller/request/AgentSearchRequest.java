package com.verta.controller.request;

import lombok.Data;

@Data
public class AgentSearchRequest {

    private String limit;

    private String offset;

    private String searchQuery;

}

