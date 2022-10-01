package com.verta.repository.jdbctemplate;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateAgentRepository implements AgentRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final AgentRowMapper agentRowMapper;

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
        return jdbcTemplate.query("select * from entity.agents", agentRowMapper);
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
}
