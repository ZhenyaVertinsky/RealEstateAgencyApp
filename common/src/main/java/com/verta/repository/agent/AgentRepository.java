package com.verta.repository.agent;

import com.verta.domain.Agent;
import com.verta.exeption.NoSuchEntityException;


import com.verta.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.verta.repository.columns.AgentTableColumns.BIRTH_DATE;
import static com.verta.repository.columns.AgentTableColumns.CHANGED;
import static com.verta.repository.columns.AgentTableColumns.CREATED;
import static com.verta.repository.columns.AgentTableColumns.ID;
import static com.verta.repository.columns.AgentTableColumns.IS_DELETED;
import static com.verta.repository.columns.AgentTableColumns.NAME;
import static com.verta.repository.columns.AgentTableColumns.PHONE;
import static com.verta.repository.columns.AgentTableColumns.REWARD;
import static com.verta.repository.columns.AgentTableColumns.SURNAME;
import static com.verta.util.UUIDGenerator.generateUUID;


@Repository
//@Primary
@RequiredArgsConstructor
public class AgentRepository implements AgentRepositoryInterface {
    private static final Logger log = Logger.getLogger(AgentRepository.class);

    @Override
    public Agent findById(Long id) {
        final String findByIdQuery = "select * from entity.agents where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return agentRowMapping(rs);
            } else {
                throw new NoSuchEntityException("Entity Agent with id " + id + " does not exist", 404, UUIDGenerator.generateUUID());
            }
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            throw new RuntimeException("DB connection process issues");
        }
    }

    @Override
    public Optional<Agent> findOne(Long id) {
        return Optional.of(findById(id));
    }

    public List<Agent> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    private Connection getConnection() throws SQLException {
        try {
            String driver = "databaseProperties.getDriverName()";

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("JDBC Driver Cannot be loaded!", e);
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String url = "databaseProperties.getUrl()";
//        String port = databaseProperties.getPort();
//        String dbName = databaseProperties.getName();
        String login = "databaseProperties.getLogin()";
        String password = "databaseProperties.getPassword()";

//        String jdbcURL = StringUtils.join(url, port, dbName);

//        return DriverManager.getConnection(jdbcURL, login, password);

        return DriverManager.getConnection(url, login, password);
    }

    private Agent agentRowMapping(ResultSet rs) throws SQLException {
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

//        return Agent.builder()
//                .id(rs.getLong(ID))
//                .agentName(rs.getString(NAME))
//                .agentSurname(rs.getString(SURNAME))
//                .birthday(rs.getTimestamp(BIRTH_DATE))
//                .agentPhone(rs.getString(PHONE))
//                .percentReward(rs.getInt(REWARD))
//                .creationDate(rs.getTimestamp(CREATED))
//                .modificationDate(rs.getTimestamp(CHANGED))
//                .isDeleted(rs.getBoolean(IS_DELETED))
//                .build();

    }

    @Override
    public List<Agent> findAll(int limit, int offset) {
        final String findAllQuery = "select * from entity.agents order by id limit " + limit + " offset " + offset;

        List<Agent> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(agentRowMapping(rs));
            }

            return result;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Agent create(Agent object) {
        final String insertQuery =
                "insert into entity.agents (agent_name, agent_surname, birthday, agent_phone, percent_reward, " +
                        "creation_date, modification_date, is_deleted)" +
                        "values (?, ?, ?, ?, ?, ?, ?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getAgentName());
            statement.setString(2, object.getAgentSurname());
            statement.setTimestamp(3, object.getBirthday());
            statement.setString(4, object.getAgentPhone());
            statement.setInt(5, object.getPercentReward());
            statement.setTimestamp(6, object.getCreationDate());
            statement.setTimestamp(7, object.getModificationDate());
            statement.setBoolean(8, object.getIsDeleted());

            //executeUpdate - for INSERT, UPDATE, DELETE
            statement.executeUpdate();
            //For select
            //statement.executeQuery();

            /*Get users last insert id for finding new object in DB*/
            ResultSet resultSet = connection.prepareStatement("SELECT currval('entity.agents_id_seq') as last_id").executeQuery();
            resultSet.next();
            long userLastInsertId = resultSet.getLong("last_id");

            return findById(userLastInsertId);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Agent update(Agent object) {
        final String updateQuery =
                "update entity.agents " +
                        "set " +
                        "agent_name = ?, agent_surname = ?, birthday = ?, agent_phone = ?, percent_reward = ?, creation_date = ?, modification_date = ?, is_deleted = ? " +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getAgentName());
            statement.setString(2, object.getAgentSurname());
            statement.setTimestamp(3, object.getBirthday());
            statement.setString(4, object.getAgentPhone());
            statement.setInt(5, object.getPercentReward());
            statement.setTimestamp(6, object.getCreationDate());
            statement.setTimestamp(7, object.getModificationDate());
            statement.setBoolean(8, object.getIsDeleted());
            statement.setLong(9, object.getId());

            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Long id) {
        final String deleteQuery = "delete from entity.agents  where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Map<String, Object> getAgentsStats() {
        final String callFunction = "select entity.get_agents_stats_average_percent_reward(?)";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(callFunction);
            statement.setBoolean(1, false);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            double functionCall = resultSet.getDouble(1);

            return Collections.singletonMap("avg", functionCall);
        } catch (SQLException e) {
            log.error("DB connection process issues", e);
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<Agent> findByLogin(String login) {
        return Optional.empty();
    }
}