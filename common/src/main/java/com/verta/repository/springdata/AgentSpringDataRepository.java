package com.verta.repository.springdata;

import com.verta.domain.Gender;
import com.verta.domain.hibernate.HibernateAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AgentSpringDataRepository extends CrudRepository<HibernateAgent, Long>,
        JpaRepository<HibernateAgent, Long>, PagingAndSortingRepository<HibernateAgent, Long> {

    HibernateAgent findByIdAndGender(Long id, Gender gender);

    // Before credentials ***
//    List<HibernateAgent> findByAgentLogin(String login);
//    List<HibernateAgent> findByAgentLoginAndAgentNameAndBirthday(String login, String name, Timestamp birthDate);
//    List<HibernateAgent> findByAgentLoginAndAgentNameOrBirthdayOrderByIdDescAgentNameDesc(String login, String name, Timestamp birthDate);

    List<HibernateAgent> findByCredentialsLogin(String login);

    List<HibernateAgent> findByCredentialsLoginAndAgentNameAndBirthday(String login, String name, Timestamp birthDate);

    List<HibernateAgent> findByCredentialsLoginAndAgentNameOrBirthdayOrderByIdDescAgentNameDesc(String login, String name, Timestamp birthDate);

    List<HibernateAgent> findByIsDeletedOrderByIdDesc(Boolean isDeleted);
    //select * from m_agents where (login = ? and name = ?) or birthday = ?

    @Query(value = "select a from HibernateAgent a")
    List<HibernateAgent> findByHQLQuery();

    @Query(value = "select * from entity.agents", nativeQuery = true)
    List<HibernateAgent> findByHQLQueryNative();

    // Before credentials ***
//    @Query(value = "select a from HibernateAgent a where a.agentLogin = :login and a.agentName = :agentName")
//    List<HibernateAgent> findByHQLQuery(String login, @Param("agentName") String name);

    @Query(value = "select a from HibernateAgent a where a.credentials.login = :login and a.agentName = :agentName")
    List<HibernateAgent> findByHQLQuery(String login, @Param("agentName") String name);

    @Query("select a.id, a.agentName from HibernateAgent a")
    List<Object[]> getPartsOfAgent();


    @Modifying
    @Query(value = "insert into entity.l_role_agent(agent_id, role_id) values (:agent_id, :role_id)", nativeQuery = true)
    int createRoleRow(@Param("agent_id") Long agentId, @Param("role_id") Long roleId);


}