package com.verta.repository.agent;

import com.verta.domain.Agent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class TestSpringAgentInterface implements AgentRepositoryInterface {

    @Override
    public Agent findById(Long id) {
        return null;
    }

    @Override
    public Optional<Agent> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Agent> findAll() {
        return null;
    }

    @Override
    public List<Agent> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public Agent create(Agent object) {
        return null;
    }

    @Override
    public Agent update(Agent object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getAgentsStats() {
        return null;
    }

    @Override
    public Optional<Agent> findByLogin(String login) {
        return Optional.empty();
    }
}
