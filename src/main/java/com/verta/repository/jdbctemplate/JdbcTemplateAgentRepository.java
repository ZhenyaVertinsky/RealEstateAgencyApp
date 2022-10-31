package com.verta.repository.jdbctemplate;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
        return jdbcTemplate.queryForObject("select * from entity.agents where id = " + id, agentRowMapper);
    }

    @Override
    public Optional<Agent> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Agent> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Agent> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from entity.agents limit" + limit + " offset " +
                offset, agentRowMapper);
    }

    @Override
    public Agent create(Agent object) {
        final String insertQuery =
                "insert into entity.agents (agent_name, agent_surname, birthday, agent_phone, percent_reward, " +
                        "creation_date, modification_date, is_deleted)" +
                        "values (:agentName, :agentSurname, :birthday, :agentPhone, :percentReward, :creationDate, " +
                        ":modificationDate, :isDeleted, :login, :password);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("agentName", object.getAgentName());
        mapSqlParameterSource.addValue("agentSurname", object.getAgentSurname());
        mapSqlParameterSource.addValue("birthday", object.getBirthday());
        mapSqlParameterSource.addValue("agentPhone", object.getAgentPhone());
        mapSqlParameterSource.addValue("percentReward", object.getPercentReward());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('entity.agents_id_seq') as last_id",
                resultSet -> {

            resultSet.next();
            return resultSet.getLong("last_id");
        });

        return findById(lastInsertId);
    }

    @Override
    public Agent update(Agent object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from entity.agents  where id " + id);
        return id;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public Map<String, Object> getAgentsStats() {
        return jdbcTemplate.query("select entity.get_agents_stats_average_percent_reward(true)",
                resultSet -> {

            resultSet.next();
            return Collections.singletonMap("avg", resultSet.getDouble(1));
        });
    }
    @Override
    public Optional<Agent> findByLogin(String login) {

        final String searchByLoginQuery = "select * from entity.agents where agent_login = :login";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("login", login);

        return Optional.of(namedParameterJdbcTemplate.queryForObject(searchByLoginQuery, mapSqlParameterSource, agentRowMapper));
    }
}
