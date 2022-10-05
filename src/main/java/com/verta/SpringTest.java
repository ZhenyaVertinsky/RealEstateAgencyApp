package com.verta;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import com.verta.service.AgentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class SpringTest {
    public static void main(String[] args) {
        // At the beginning of main ClassPathXmlApplicationContext read all from application-context.xml create
        // Beans and put in application-context.xml all created Beans
//        ClassPathXmlApplicationContext ClassPathXmlApplicationContext = new ClassPathXmlApplicationContext
//                ("classpath:application-context.xml");
//
//        Agent agent1 = ClassPathXmlApplicationContext.getBean("agent1", Agent.class);
//        Agent agent2 = ClassPathXmlApplicationContext.getBean("agent2", Agent.class);
//
//        System.out.println(agent1);
//        System.out.println(agent2);


        // We have a class which work with annotation
        // We scan package com.verta
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.verta");

//        AgentRepositoryInterface agentRepository = annotationConfigApplicationContext.
//                getBean(AgentRepositoryInterface.class);
//
//        for (Agent agent : agentRepository.findAll()) {
//            System.out.println(agent);
//        }

        // We have a class which implements AgentService - which have annotation @Service - it is a Bean
        // Next we say "context give us spring Bean, and we call on him a method"
        AgentService agentService = annotationConfigApplicationContext.getBean(AgentService.class);

//        agentService.findById(156373L);

        Map<String, Object> agentStats = agentService.getAgentStats();

        for (Agent agent : agentService.findAll()) {
            System.out.println(agent);
        }

        for (Map.Entry<String, Object> stringObjectEntry : agentStats.entrySet()) {
            System.out.println(stringObjectEntry.getKey() + " " + stringObjectEntry.getValue());
        }

        Agent agent = new Agent();
        agent.setAgentName("Test");
        agent.setAgentSurname("Template");
        agent.setBirthday(new Timestamp(new Date().getTime()));
        agent.setAgentPhone("375330000000");
        agent.setPercentReward(30);
        agent.setCreationDate(new Timestamp(new Date().getTime()));
        agent.setModificationDate(new Timestamp(new Date().getTime()));
        agent.setIsDeleted(false);

        System.out.println(agent);

        Agent agent1 = agentService.creat(agent);
        System.out.println(agent1);




    }
}
