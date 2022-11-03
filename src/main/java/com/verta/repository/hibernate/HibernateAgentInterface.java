package com.verta.repository.hibernate;

import com.verta.domain.Agent;
import com.verta.domain.hibernate.HibernateAgent;
import com.verta.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface HibernateAgentInterface extends CRUDRepository<Long, HibernateAgent> {

    Map<String, Object> getAgentsStats();

    Optional<Agent> findByLogin(String login);

}