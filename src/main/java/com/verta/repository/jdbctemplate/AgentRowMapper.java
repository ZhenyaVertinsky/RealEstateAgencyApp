package com.verta.repository.jdbctemplate;

import com.verta.domain.Agent;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.verta.repository.agent.AgentTableColumns.BIRTH_DATE;
import static com.verta.repository.agent.AgentTableColumns.CHANGED;
import static com.verta.repository.agent.AgentTableColumns.CREATED;
import static com.verta.repository.agent.AgentTableColumns.ID;
import static com.verta.repository.agent.AgentTableColumns.IS_DELETED;
import static com.verta.repository.agent.AgentTableColumns.NAME;
import static com.verta.repository.agent.AgentTableColumns.PHONE;
import static com.verta.repository.agent.AgentTableColumns.REWARD;
import static com.verta.repository.agent.AgentTableColumns.SURNAME;

@Component

public class AgentRowMapper implements RowMapper<Agent> {

    @Override
    public Agent mapRow(ResultSet rs, int i) throws SQLException {
        Agent agent = new Agent();

        agent.setId(rs.getLong(ID));
        agent.setAgentName(rs.getString(NAME));
        agent.setAgentSurname(rs.getString(SURNAME));
        agent.setBirthday(rs.getTimestamp(BIRTH_DATE));
        agent.setAgentPhone(rs.getString(PHONE));
        agent.setPercentReward(rs.getInt(REWARD));
        agent.setCreationDate(rs.getTimestamp(CREATED));
        agent.setModificationDate(rs.getTimestamp(CHANGED));
        agent.setIsDeleted(rs.getBoolean(IS_DELETED));

        return agent;
    }
}

