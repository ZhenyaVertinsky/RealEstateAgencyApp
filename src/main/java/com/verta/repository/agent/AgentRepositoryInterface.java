package com.verta.repository.agent;

import com.verta.domain.Agent;
import com.verta.repository.CRUDRepository;

import java.util.Map;


public interface AgentRepositoryInterface extends CRUDRepository<Long, Agent> {

    Map<String, Object> getAgentsStats();


}