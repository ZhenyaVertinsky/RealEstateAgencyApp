package com.verta;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepository;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AgentRepository agentRepository = new AgentRepository();

        List<Agent> all = agentRepository.findAll();

        for (Agent agent : all) {
            System.out.println(agent);
        }

        agentRepository.findOne(1L).ifPresent(System.out::println);

        Agent agent = new Agent();
        agent.setAgentName("New");
        agent.setAgentSurname("Agent");
        agent.setBirthday(new Timestamp(new Date().getTime()));
        agent.setAgentPhone("375290000000");
        agent.setPercentReward(10);
        agent.setCreationDate(new Timestamp(new Date().getTime()));
        agent.setModificationDate(new Timestamp(new Date().getTime()));
        agent.setDeleted(false);

        System.out.println(agent);

        Agent agent1 = agentRepository.create(agent);
        System.out.println(agent1);

        agent.setId(agent1.getId());
        agent.setAgentName("Update Prepared");
        agent.setModificationDate(new Timestamp(new Date().getTime()));

//        Agent agent2 = agentRepository.update(agent);
//
//        System.out.println(agent2);
//
//        agentRepository.delete(7L);

        Map<String, Object> agentStats =
                agentRepository.getAgentsStats();

        for (Map.Entry<String, Object> stringObjectEntry : agentStats.entrySet()) {
            System.out.println(stringObjectEntry.getValue());
        }
    }
}