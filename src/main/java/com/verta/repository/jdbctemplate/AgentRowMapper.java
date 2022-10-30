package com.verta.repository.jdbctemplate;

import com.verta.domain.Agent;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.verta.repository.columns.AgentTableColumns.BIRTH_DATE;
import static com.verta.repository.columns.AgentTableColumns.CHANGED;
import static com.verta.repository.columns.AgentTableColumns.CREATED;
import static com.verta.repository.columns.AgentTableColumns.ID;
import static com.verta.repository.columns.AgentTableColumns.IS_DELETED;
import static com.verta.repository.columns.AgentTableColumns.NAME;
import static com.verta.repository.columns.AgentTableColumns.PHONE;
import static com.verta.repository.columns.AgentTableColumns.REWARD;
import static com.verta.repository.columns.AgentTableColumns.SURNAME;

@Component

public class AgentRowMapper implements RowMapper<Agent> {

    private static final Logger Log = Logger.getLogger(AgentRowMapper.class);

    @Override
    public Agent mapRow(ResultSet rs, int i) throws SQLException {
        Log.info("Agent row mapping start");

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

        Log.info("Agent row mapping end");
        return agent;
    }
}

