package com.verta.service;

import com.verta.domain.Agent;

import java.util.List;
import java.util.Map;

public interface AgentService {

    List<Agent> findAll();

    Map<String, Object> getAgentStats();

    Agent creat (Agent object);

    Agent findById (Long agentId);

    List<Agent> search(int limit, int offset);
}
