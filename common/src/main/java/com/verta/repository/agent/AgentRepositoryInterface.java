package com.verta.repository.agent;

import com.verta.domain.Agent;
import com.verta.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;


public interface AgentRepositoryInterface extends CRUDRepository<Long, Agent> {

    Map<String, Object> getAgentsStats();

    Optional<Agent> findByLogin(String login);


}