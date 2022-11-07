package com.verta.controller.request;

import com.verta.domain.Gender;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AgentCreateRequest {

    private String agentName;

    private String agentSurname;

    private Timestamp birthday;

    private Integer percentReward;

    private Gender gender;
}
